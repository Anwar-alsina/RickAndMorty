package com.example.rickmorty.episode

import com.airbnb.epoxy.EpoxyController
import com.example.rickmorty.R
import com.example.rickmorty.databinding.ModelCharacterListItemSquareBinding
import com.example.rickmorty.domain.models.Characters
import com.example.rickmorty.epoxy.ViewBindingKotlinModel
import com.squareup.picasso.Picasso

class EpisodeDetailEpoxyController(
    val characterList: List<Characters>
): EpoxyController() {
    override fun buildModels() {
        characterList.forEach {
            CharacterEpoxyModel(
                imageurl = it.image,
                name = it.name
            ).id(it.id).addTo(this)
        }
    }

    data class CharacterEpoxyModel(
        val imageurl: String,
        val name: String
    ): ViewBindingKotlinModel<ModelCharacterListItemSquareBinding>(R.layout.model_character_list_item_square){
        override fun ModelCharacterListItemSquareBinding.bind() {
            Picasso.get().load(imageurl).into(characterImageView)
            characterNameTextView.text = name
        }

    }
}
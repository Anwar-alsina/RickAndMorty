package com.example.rickmorty.characters.search

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging3.PagingDataEpoxyController
import com.example.rickmorty.R
import com.example.rickmorty.characters.CharacterListPagingEpoxyController
import com.example.rickmorty.databinding.ModelCharacterListItemBinding
import com.example.rickmorty.databinding.ModelLocalExceptionErrorStateBinding
import com.example.rickmorty.domain.models.Characters
import com.example.rickmorty.epoxy.LoadingEpoxyModel
import com.example.rickmorty.epoxy.ViewBindingKotlinModel
import com.example.rickmorty.network.response.GetCharacterByIdResponse
import com.squareup.picasso.Picasso

class CharacterSearchEpoxyController(
    private val onCharacterSelected: (Int) -> Unit
) : PagingDataEpoxyController<Characters>() {

    var localException: CharacterPagingSource.LocalException? = null
        set(value) {
            field = value
            if (localException != null) {
                requestForcedModelBuild()
            }
        }

    override fun buildItemModel(
        currentPosition: Int,
        item: Characters?
    ): EpoxyModel<*> {
        return CharacterListPagingEpoxyController.CharacterGridItemEpoxyModel(
            characterId = item!!.id,
            imageUrl = item.image,
            name = item.name,
            onCharacterSelected = onCharacterSelected
        )
            .id(item.id)
    }

    override fun addModels(models: List<EpoxyModel<*>>) {

        if (models.isEmpty()){
            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }

        super.addModels(models)
    }
    data class CharacterGridItemEpoxyModel(
        val imageUrl: String,
        val name: String,
        val onCharacterSelected: (Int) -> Unit,
        val characterId: Int
    ): ViewBindingKotlinModel<ModelCharacterListItemBinding>(R.layout.model_character_list_item){
        override fun ModelCharacterListItemBinding.bind() {
            Picasso.get().load(imageUrl).into(characterImageView)
            characterNameTextView.text = name

            root.setOnClickListener {
                onCharacterSelected(characterId)
            }
        }
    }

}
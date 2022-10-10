package com.example.rickmorty.network

import android.view.View
import com.airbnb.epoxy.EpoxyController
import com.example.rickmorty.R
import com.example.rickmorty.databinding.ModelCharacterDetailsDataPointBinding
import com.example.rickmorty.databinding.ModelCharacterImageDetailsBinding
import com.example.rickmorty.databinding.ModelDetailsHeaderBinding
import com.example.rickmorty.epoxy.LoadingEpoxyModel
import com.example.rickmorty.epoxy.ViewBindingKotlinModel
import com.example.rickmorty.network.response.GetCharacterByIdResponse
import com.squareup.picasso.Picasso
import retrofit2.Response

class CharacterDetailsEpoxyController: EpoxyController() {
    var isLoading:Boolean = true
    set(value){
        field = value
        if (field){
            requestModelBuild()
        }
    }
    var characterResponse:GetCharacterByIdResponse? = null
    set(value) {
        field= value
        if (field != null){
            isLoading = false
            requestModelBuild()
        }
    }


    override fun buildModels() {
        if (isLoading){
            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }
        if (characterResponse == null){
            //to do error state
            return
        }

        //Header Model
        HeaderEpoxyModel(
            name = characterResponse!!.name,
            gender = characterResponse!!.gender,
            status = characterResponse!!.status
        ).id("header").addTo(this)

        //Image Model
        ImageEpoxyModel(
            image = characterResponse!!.image
        ).id("image").addTo(this)

        //Data Point Model
        DataPointEpoxyModel(
            title = "Origin",
            description = characterResponse!!.origin.name
        ).id("data_point_1").addTo(this)

        DataPointEpoxyModel(
            title = "Species",
            description = characterResponse!!.species
        )

    }

    data class HeaderEpoxyModel(
        val name: String,
        val gender: String,
        val status: String
    ): ViewBindingKotlinModel<ModelDetailsHeaderBinding>(R.layout.model_details_header){
        override fun ModelDetailsHeaderBinding.bind() {
            tvName.text = name
            tvStatus.text = status

            if (gender.equals("male", ignoreCase = true)){
                ivGender.setImageResource(R.drawable.mars)
            }else{
                ivGender.setImageResource(R.drawable.femenine)
            }
        }
    }

    data class ImageEpoxyModel(
        val image: String
    ): ViewBindingKotlinModel<ModelCharacterImageDetailsBinding>(R.layout.model_character_image_details){
        override fun ModelCharacterImageDetailsBinding.bind() {
            Picasso.get().load(image).into(ivPerson);
        }

    }

    data class DataPointEpoxyModel(
        val title:String,
        val description: String
    ): ViewBindingKotlinModel<ModelCharacterDetailsDataPointBinding>(R.layout.model_character_details_data_point){
        override fun ModelCharacterDetailsDataPointBinding.bind() {
            tvLabel.text = title
            tvSpeciesDesc.text = description
            tvOrigin.text = title
            tvOriginDesc.text = description
        }
    }


}
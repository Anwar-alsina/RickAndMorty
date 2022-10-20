package com.example.rickmorty.episode

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging3.PagedListEpoxyController
import com.airbnb.epoxy.paging3.PagingDataEpoxyController
import com.example.rickmorty.R
import com.example.rickmorty.databinding.ModelEpisodeListItemBinding
import com.example.rickmorty.domain.models.Episode
import com.example.rickmorty.epoxy.ViewBindingKotlinModel

class EpisodeListEpoxyController:PagingDataEpoxyController<Episode>() {
    override fun buildItemModel(currentPosition: Int, item: Episode?): EpoxyModel<*> {

        return EpisodeListEpoxyModel(
            episode = item!!,
            onClick = {episodeId ->
                //todo
            }
        ).id("episode ${item.id}")
    }

    data class EpisodeListEpoxyModel(
        val episode: Episode,
        val onClick: (Int) -> Unit
    ): ViewBindingKotlinModel<ModelEpisodeListItemBinding>(R.layout.model_episode_list_item){
        override fun ModelEpisodeListItemBinding.bind() {
            episodeNameTextView.text = episode.name
            episodeAirDateTextView.text = episode.airDate
            episodeNumberTextView.text = episode.episode
        }

    }
}
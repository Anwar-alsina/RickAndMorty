package com.example.rickmorty.episode

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging3.PagedListEpoxyController
import com.airbnb.epoxy.paging3.PagingDataEpoxyController
import com.example.rickmorty.R
import com.example.rickmorty.databinding.ModelEpisodeListItemBinding
import com.example.rickmorty.databinding.ModelEpisodeListTitleBinding
import com.example.rickmorty.domain.models.Episode
import com.example.rickmorty.epoxy.ViewBindingKotlinModel

class EpisodeListEpoxyController(
    private val onEpisodeClicked: (Int) -> Unit
):PagingDataEpoxyController<EpisodesUiModel>() {
    override fun buildItemModel(currentPosition: Int, item: EpisodesUiModel?): EpoxyModel<*> {

        return when(item!!){
            is EpisodesUiModel.Item ->{
                val episode = (item as EpisodesUiModel.Item).episode
                EpisodeListEpoxyModel(
                    episode = episode,
                    onClick = {episodeId ->
                        onEpisodeClicked(episodeId)
                    }
                ).id("episode ${episode.id}")
            }
            is EpisodesUiModel.Header -> {
                val headerText = (item as EpisodesUiModel.Header).text
                EpisodeListTitleEpoxyModel(headerText).id("header_$headerText")
            }
        }
    }

    data class EpisodeListEpoxyModel(
        val episode: Episode,
        val onClick: (Int) -> Unit
    ): ViewBindingKotlinModel<ModelEpisodeListItemBinding>(R.layout.model_episode_list_item){
        override fun ModelEpisodeListItemBinding.bind() {
            episodeNameTextView.text = episode.name
            episodeAirDateTextView.text = episode.airDate
            episodeNumberTextView.text = episode.getFormattedSeasonTruncated()

            root.setOnClickListener { onClick(episode.id) }
        }

    }

    data class EpisodeListTitleEpoxyModel(
        val title : String
    ): ViewBindingKotlinModel<ModelEpisodeListTitleBinding>(R.layout.model_episode_list_title){
        override fun ModelEpisodeListTitleBinding.bind() {
            textView.text = title
        }

    }


}


package com.example.rickmorty.episode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import com.example.rickmorty.Constants
import com.example.rickmorty.domain.models.Episode
import kotlinx.coroutines.flow.map

class EpisodeViewModel: ViewModel() {

    private val repository = EpisodeRepository()
    val flow = Pager(
        PagingConfig(
            pageSize = Constants.PAGE_SIZE,
            prefetchDistance = Constants.PREFETCH_DISTANCE,
            enablePlaceholders = false
        )
    ){
        EpisodePagingSource(repository)
    }.flow.cachedIn(viewModelScope).map {
        it.insertSeparators { episodesUiModel: EpisodesUiModel?, episodesUiModel2: EpisodesUiModel? ->

            //Initial separator
            if (episodesUiModel == null){
                return@insertSeparators EpisodesUiModel.Header("Season 1")
            }
            if (episodesUiModel2 == null){
                return@insertSeparators null
            }
            if (episodesUiModel is EpisodesUiModel.Header || episodesUiModel2 is EpisodesUiModel.Header){
                return@insertSeparators null
            }

            val season1 = (episodesUiModel as EpisodesUiModel.Item).episode
            val season2 = (episodesUiModel2 as EpisodesUiModel.Item).episode

            return@insertSeparators if (season2.seasonNumber != season1.seasonNumber){
                EpisodesUiModel.Header("Season ${season2.seasonNumber}")
            }else{
                null
            }
        }
    }
}
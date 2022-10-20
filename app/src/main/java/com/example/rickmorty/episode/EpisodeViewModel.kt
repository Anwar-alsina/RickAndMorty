package com.example.rickmorty.episode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.rickmorty.Constants

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
    }.flow.cachedIn(viewModelScope)
}
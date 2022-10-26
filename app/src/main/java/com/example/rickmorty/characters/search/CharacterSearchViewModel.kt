package com.example.rickmorty.characters.search

import android.text.InputFilter
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.rickmorty.Constants
import com.example.rickmorty.Event

class CharacterSearchViewModel: ViewModel() {
    private var currentUserSearch: String = ""
    private var pagingSource: CharacterPagingSource? = null
    get() {
        if (field == null || field?.invalid == true) {
            field = CharacterPagingSource(currentUserSearch) { localException ->
                //notify our livedata of an issue from the paging source
                _localExceptionEventLiveData.postValue(Event(localException))
            }
        }
        return field
    }

    val flow = Pager(
        PagingConfig(
            pageSize = Constants.PAGE_SIZE,
            prefetchDistance = Constants.PREFETCH_DISTANCE,
            enablePlaceholders = false
        )
    ){
        pagingSource!!
    }.flow.cachedIn(viewModelScope)

    //For error handling propagation
    private val _localExceptionEventLiveData = MutableLiveData<Event<CharacterPagingSource.LocalException>>()
    val localExceptionLiveData: LiveData<Event<CharacterPagingSource.LocalException>> = _localExceptionEventLiveData
    fun submitQuery(userSearch: String) {
        currentUserSearch = userSearch
        pagingSource?.invalidate()
    }
}
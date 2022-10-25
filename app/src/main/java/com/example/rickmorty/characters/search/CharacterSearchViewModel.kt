package com.example.rickmorty.characters.search

import android.text.InputFilter
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.rickmorty.Constants

class CharacterSearchViewModel: ViewModel() {
    private var currentUserSearch: String = ""
    private var pagingSource: CharacterPagingSource? = null
    get() {
        if (field == null || field?.invalid == true) {
            field = CharacterPagingSource(currentUserSearch) { localException ->
                Log.e("Local", localException.toString())
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


    fun submitQuery(userSearch: String) {
        currentUserSearch = userSearch
        pagingSource?.invalidate()
    }
}
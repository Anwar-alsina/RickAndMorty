package com.example.rickmorty.episode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmorty.domain.models.Episode
import kotlinx.coroutines.launch

class EpisodeDetailsViewModel: ViewModel() {

    private val repository = EpisodeRepository()

    private var _episodeLiveData = MutableLiveData<Episode?>()
    val episodeLiveData: LiveData<Episode?> = _episodeLiveData

    fun fetchEpisode(episodeId: Int){
        viewModelScope.launch {
            val episode = repository.getEpisodeById((episodeId))

            _episodeLiveData.postValue(episode)
        }
    }
}
package com.example.rickmorty.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmorty.domain.mappers.CharacterMapper
import com.example.rickmorty.domain.models.Characters
import com.example.rickmorty.network.RickMortyCache
import com.example.rickmorty.network.response.GetCharacterByIdResponse
import com.example.rickmorty.repository.SharedRepository
import kotlinx.coroutines.launch

class SharedViewModel:ViewModel() {

    private val repository = SharedRepository()

    private val _characterByLiveData = MutableLiveData<Characters?>()
    val characterByIdLiveData = _characterByLiveData

    fun fetchCharacter(characterId: Int) {
        //Check the cache for the character
        val cachedCharacters = RickMortyCache.characterMap[characterId]
        if (cachedCharacters != null){
            _characterByLiveData.postValue(cachedCharacters)
            return
        }

        viewModelScope.launch {
            val response = repository.getCharacterById(characterId)
            characterByIdLiveData.postValue(response)

            //Update the cache if not null character is received
            response?.let {
                RickMortyCache.characterMap[characterId] = it
            }
        }
    }
}




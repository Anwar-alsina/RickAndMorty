package com.example.rickmorty.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmorty.domain.models.Characters
import com.example.rickmorty.repository.SharedRepository
import kotlinx.coroutines.launch

class SharedViewModel:ViewModel() {

    private val repository = SharedRepository()

    private val _characterByLiveData = MutableLiveData<Characters?>()
    val characterByIdLiveData = _characterByLiveData

   fun fetchCharacter(characterId: Int) = viewModelScope.launch {
       val character = repository.getCharacterById(characterId)
       _characterByLiveData.postValue(character)
   }
}




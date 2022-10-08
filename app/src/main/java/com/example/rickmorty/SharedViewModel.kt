package com.example.rickmorty

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SharedViewModel:ViewModel() {

    private val repository = SharedRepository()

    private val _characterByLiveData = MutableLiveData<GetCharacterByIdResponse?>()
    val characterByIdLiveData = _characterByLiveData

    fun refreshCharacter(characterId:Int){
        viewModelScope.launch {
            val response = repository.getCharacterById(characterId)
            characterByIdLiveData.postValue(response)
        }
    }
}
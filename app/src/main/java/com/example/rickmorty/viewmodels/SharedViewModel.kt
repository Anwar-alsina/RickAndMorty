package com.example.rickmorty.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmorty.network.response.GetCharacterByIdResponse
import com.example.rickmorty.repository.SharedRepository
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
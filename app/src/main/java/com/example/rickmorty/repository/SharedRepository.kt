package com.example.rickmorty.repository

import com.example.rickmorty.domain.mappers.CharacterMapper
import com.example.rickmorty.domain.models.Characters
import com.example.rickmorty.network.NetworkLayer


class SharedRepository {

    suspend fun getCharacterById(characterId: Int): Characters? {
        val request = NetworkLayer.apiClient.getCharacterById(characterId)

        if (request.failed){
            return null
        }
        if (!request.isSuccessful){
            return null
        }

        return  CharacterMapper.buildFrom(response = request.body)
    }
}

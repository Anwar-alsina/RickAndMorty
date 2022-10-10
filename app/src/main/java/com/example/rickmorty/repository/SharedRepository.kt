package com.example.rickmorty.repository

import com.example.rickmorty.network.NetworkLayer
import com.example.rickmorty.network.response.GetCharacterByIdResponse

class SharedRepository {

    suspend fun getCharacterById(characterId: Int): GetCharacterByIdResponse?{
        val request = NetworkLayer.apiClient.getCharacterById(characterId)

        if (!request.isSuccessful){
            return null
        }
        if (request.failed){
            return null
        }

        return request.body
    }

}

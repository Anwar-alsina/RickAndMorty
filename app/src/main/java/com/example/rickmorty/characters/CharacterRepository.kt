package com.example.rickmorty.characters

import com.example.rickmorty.network.NetworkLayer
import com.example.rickmorty.network.response.GetCharactersPageResponse

class CharacterRepository {

    suspend fun getCharactersPage(pageIndex:Int): GetCharactersPageResponse?{
        val request = NetworkLayer.apiClient.getCharactersPage(pageIndex)

        if (request.failed || !request.isSuccessful){
            return null
        }
        return request.body
    }
}
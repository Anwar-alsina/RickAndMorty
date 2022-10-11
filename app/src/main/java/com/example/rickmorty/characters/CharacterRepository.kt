package com.example.rickmorty.characters

import com.example.rickmorty.network.NetworkLayer

class CharacterRepository {

    suspend fun getCharactersPage(pageIndex:Int): GetCharactersPageResponse?{
        val request = NetworkLayer.apiClient.getCharactersPage(pageIndex)

        if (request.failed || !request.isSuccessful){
            return null
        }
        return request.body
    }
}
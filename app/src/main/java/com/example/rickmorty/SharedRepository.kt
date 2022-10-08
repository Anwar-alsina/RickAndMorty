package com.example.rickmorty

class SharedRepository {

    suspend fun getCharacterById(characterId: Int):GetCharacterByIdResponse?{
        val request = NetworkLayer.apiClient.getCharacterById(characterId)

        if (request.isSuccessful){
            return null
        }
        if (request.failed){
            return null
        }

        return request.body
    }

}

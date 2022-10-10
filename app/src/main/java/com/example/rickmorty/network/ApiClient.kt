package com.example.rickmorty.network

import com.example.rickmorty.network.response.GetCharacterByIdResponse
import com.example.rickmorty.network.NetworkLayer.rickAndMortyService
import retrofit2.Response

class ApiClient(rickAndMortyService: RickAndMortyService) {

    suspend fun getCharacterById(characterId: Int): SimpleResponse<GetCharacterByIdResponse> {
        return safeApiCall { rickAndMortyService.getCharacterById(characterId) }
    }

    private inline fun<T> safeApiCall(apiCall: () -> Response<T>): SimpleResponse<T> {
        return try {
            SimpleResponse.success(apiCall.invoke())
        }catch (e:Exception){
            SimpleResponse.failure(e)
        }
    }

}
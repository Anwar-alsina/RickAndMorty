package com.example.rickmorty.network

import com.example.rickmorty.network.response.GetCharactersPageResponse
import com.example.rickmorty.network.response.GetCharacterByIdResponse
import com.example.rickmorty.network.NetworkLayer.rickAndMortyService
import com.example.rickmorty.network.response.GetEpisodeByIdResponse
import com.example.rickmorty.network.response.GetEpisodePageResponse
import retrofit2.Response

class ApiClient(rickAndMortyService: RickAndMortyService) {

    suspend fun getCharacterById(characterId: Int): SimpleResponse<GetCharacterByIdResponse> {
        return safeApiCall {
            rickAndMortyService.getCharacterById(characterId)
        }
    }

    suspend fun getCharactersPage(pageIndex: Int): SimpleResponse<GetCharactersPageResponse>{
        return safeApiCall {
            rickAndMortyService.getCharactersPage(pageIndex)
        }
    }

    suspend fun getEpisodeById(episodeId: Int): SimpleResponse<GetEpisodeByIdResponse>{
        return safeApiCall {
            rickAndMortyService.getEpisodeById(episodeId)
        }
    }
    suspend fun getEpisodeRange(episodeRange : String): SimpleResponse<List<GetEpisodeByIdResponse>>{
        return safeApiCall {
            rickAndMortyService.getEpisodeRange(episodeRange)
        }
    }

    suspend fun getEpisodePage(pageIndex: Int): SimpleResponse<GetEpisodePageResponse>{
        return safeApiCall {
            rickAndMortyService.getEpisodePage(pageIndex)
        }
    }

    private inline fun<T> safeApiCall(apiCall: () -> Response<T>): SimpleResponse<T> {
        return try {
            SimpleResponse.success(apiCall.invoke())
        }catch (e:Exception){
            SimpleResponse.failure(e)
        }
    }

}

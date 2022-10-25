package com.example.rickmorty.episode

import com.example.rickmorty.domain.mappers.EpisodeMapper
import com.example.rickmorty.domain.models.Episode
import com.example.rickmorty.network.NetworkLayer
import com.example.rickmorty.network.response.GetCharacterByIdResponse
import com.example.rickmorty.network.response.GetEpisodeByIdResponse
import com.example.rickmorty.network.response.GetEpisodePageResponse

class EpisodeRepository {
    suspend fun fetchEpisodePage(pageIndex: Int): GetEpisodePageResponse?{
        val pageRequest = NetworkLayer.apiClient.getEpisodePage(pageIndex)

        if (!pageRequest.isSuccessful){
            return null
        }
        return pageRequest.body
    }

    suspend fun getEpisodeById(episodeId: Int): Episode?{
        val request = NetworkLayer.apiClient.getEpisodeById(episodeId)

        if (!request.isSuccessful){
            return null
        }

        val characterList = getCharactersFromEpisodeResponse(request.body)
        return EpisodeMapper.buildFrom(
            networkEpisode = request.body,
            networkCharacters = characterList
        )
    }

    private suspend fun getCharactersFromEpisodeResponse(
        episodeByIdResponse: GetEpisodeByIdResponse
    ): List<GetCharacterByIdResponse> {
        val characterList = episodeByIdResponse.characters.map {
            it.substring(startIndex = it.lastIndexOf("/") + 1)
        }

        val request = NetworkLayer.apiClient.getMultipleCharacters(characterList)
        return request.bodyNullable ?: emptyList()
    }
}

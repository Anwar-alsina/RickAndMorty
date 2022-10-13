package com.example.rickmorty.repository

import com.example.rickmorty.domain.mappers.CharacterMapper
import com.example.rickmorty.domain.models.Characters
import com.example.rickmorty.network.NetworkLayer
import com.example.rickmorty.network.response.GetCharacterByIdResponse
import com.example.rickmorty.network.response.GetEpisodeByIdResponse


class SharedRepository {

    suspend fun getCharacterById(characterId: Int): Characters? {
        val request = NetworkLayer.apiClient.getCharacterById(characterId)

        if (request.failed){
            return null
        }
        if (!request.isSuccessful){
            return null
        }

        val networkEpisodes = getEpisodeFromCharacterResponse(request.body)

        return  CharacterMapper.buildFrom(
            response = request.body,
            episodes = networkEpisodes
            )
    }

    private suspend fun getEpisodeFromCharacterResponse(characterResponse: GetCharacterByIdResponse): List<GetEpisodeByIdResponse> {
        val episodeRange = characterResponse.episode.map {
            it.substring(it.lastIndexOf("/") + 1)
        }.toString()

        val request = NetworkLayer.apiClient.getEpisodeRange(episodeRange)

        if (request.failed  || !request.isSuccessful){
            return emptyList()
        }
        return request.body
    }
}

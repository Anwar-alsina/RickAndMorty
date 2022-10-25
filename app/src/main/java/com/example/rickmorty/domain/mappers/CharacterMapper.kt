package com.example.rickmorty.domain.mappers

import com.example.rickmorty.domain.models.Characters
import com.example.rickmorty.network.response.GetCharacterByIdResponse
import com.example.rickmorty.network.response.GetEpisodeByIdResponse

object CharacterMapper {

    fun buildFrom(
        response: GetCharacterByIdResponse,
        episodes: List<GetEpisodeByIdResponse> = emptyList()
    ): Characters {
        return Characters(
            episodeList = episodes.map{
                 EpisodeMapper.buildFrom(it)
            },
            gender = response.gender,
            id = response.id,
            image = response.image,
            location = Characters.Location(
                name = response.location.name,
                url = response.location.url
            ),
            name = response.name,
            origin =Characters.Origin(
                name = response.origin.name,
                url = response.origin.url
            ),
            species = response.species,
            status = response.status
        )
    }
}

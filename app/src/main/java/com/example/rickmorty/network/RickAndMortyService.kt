package com.example.rickmorty.network

import com.example.rickmorty.network.response.GetCharactersPageResponse
import com.example.rickmorty.network.response.GetCharacterByIdResponse
import com.example.rickmorty.network.response.GetEpisodeByIdResponse
import com.example.rickmorty.network.response.GetEpisodePageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyService {
    @GET("character/{character-id}")
    suspend fun getCharacterById(
        @Path("character-id") characterId:Int
    ): Response<GetCharacterByIdResponse>

    @GET("character")
    suspend fun getCharactersPage(
        @Query("page")pageIndex: Int
    ):Response<GetCharactersPageResponse>


    @GET("character")
    suspend fun getCharactersPage(
        @Query("name")characterName: String,
        @Query("page")pageIndex: Int
    ):Response<GetCharactersPageResponse>

    @GET("character/{list}")
    suspend fun getMultipleCharacters(
        @Path("list") characterList: List<String>
    ): Response<List<GetCharacterByIdResponse>>


    @GET("episode/{episode_id}")
    suspend fun getEpisodeById(
        @Path("episode_id")episodeId: Int
    ): Response<GetEpisodeByIdResponse>

    @GET("episode/{episode_range}")
    suspend fun getEpisodeRange(
        @Path("episode_range")episodeRange: String
    ): Response<List<GetEpisodeByIdResponse>>

    @GET("episode/")
    suspend fun getEpisodePage(
        @Query("page")pageIndex: Int
    ): Response<GetEpisodePageResponse>
}
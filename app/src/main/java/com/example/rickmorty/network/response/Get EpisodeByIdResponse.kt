package com.example.rickmorty.network.response

data class GetEpisodeByIdResponse (
    val air_date : String,
    val characters : List<String> = listOf(),
    val created: String,
    val episode: String,
    val id: Int,
    val name : String,
    val url: String
    )
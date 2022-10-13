package com.example.rickmorty.domain.models


data class Characters (
    val episodeList: List<Episode> = listOf(),
    val gender: String,
    val id: Int= 0,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
        ){

    data class Location(
        val name: String,
        val url: String
    )

    data class Origin(
        val name: String,
        val url: String
    )



}


package com.example.rickmorty.network.response

import com.example.rickmorty.network.Location
import com.example.rickmorty.network.Origin

data class GetCharacterByIdResponse(
    val created: String,
    val episode: List<String> = listOf(),
    val gender: String,
    val id: Int= 0,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)
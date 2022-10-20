package com.example.rickmorty.network

import com.example.rickmorty.domain.models.Characters


object RickMortyCache {

    val characterMap = mutableMapOf<Int, Characters>()
}
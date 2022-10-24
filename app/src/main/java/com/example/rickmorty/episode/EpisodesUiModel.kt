package com.example.rickmorty.episode

import com.example.rickmorty.domain.models.Episode

sealed class EpisodesUiModel {
    class Item(val episode: Episode): EpisodesUiModel()
    class Header(val text : String): EpisodesUiModel()
}


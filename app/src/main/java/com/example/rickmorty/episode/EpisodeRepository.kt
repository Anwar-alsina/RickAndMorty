package com.example.rickmorty.episode

import com.example.rickmorty.network.NetworkLayer
import com.example.rickmorty.network.response.GetEpisodePageResponse

class EpisodeRepository {
    suspend fun fetchEpisodePage(pageIndex: Int): GetEpisodePageResponse?{
        val pageRequest = NetworkLayer.apiClient.getEpisodePage(pageIndex)

        if (!pageRequest.isSuccessful){
            return null
        }
        return pageRequest.body
    }
}
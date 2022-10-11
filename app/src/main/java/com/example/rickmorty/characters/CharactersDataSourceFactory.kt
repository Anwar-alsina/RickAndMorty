package com.example.rickmorty.characters

import androidx.paging.DataSource
import com.example.rickmorty.network.response.GetCharacterByIdResponse
import kotlinx.coroutines.CoroutineScope

class CharactersDataSourceFactory(
    private val coroutineScope: CoroutineScope,
    private val repository: CharacterRepository
) : DataSource.Factory<Int,GetCharacterByIdResponse>(){
    override fun create(): DataSource<Int, GetCharacterByIdResponse> {
        return CharacterDataSource(coroutineScope, repository)
    }


}
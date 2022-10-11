package com.example.rickmorty.characters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.airbnb.epoxy.EpoxyRecyclerView
import com.example.rickmorty.R

class CharacterListActivity : AppCompatActivity() {

    private val epoxyController = CharacterListPagingEpoxyController()
    private val mCharacterViewModel: CharactersViewModel by lazy {
        ViewModelProvider(this)[CharactersViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_list)

        mCharacterViewModel.charactersPagedListLiveData.observe(this) { pagedList ->
            epoxyController.submitList(pagedList)
        }

        findViewById<EpoxyRecyclerView>(R.id.epoxyRecyclerview).setController(epoxyController)

    }
}
package com.example.rickmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.rickmorty.databinding.ActivityMainBinding
import com.example.rickmorty.network.CharacterDetailsEpoxyController
import com.example.rickmorty.viewmodels.SharedViewModel
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val mSharedViewModel by lazy {
        ViewModelProvider(this)[SharedViewModel::class.java]
    }
    private val epoxyController = CharacterDetailsEpoxyController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        mSharedViewModel.characterByIdLiveData.observe(this) { character ->

            epoxyController.character = character

            if (character == null) {
                Toast.makeText(
                    this@MainActivity,
                    "Unsuccessful network request",
                    Toast.LENGTH_LONG
                ).show()
                return@observe
            }
        }

        val id = intent.getIntExtra(Constants.INTENT_EXTRA_CHARACTER_ID, 1)
        mSharedViewModel.refreshCharacter(characterId = id)
        val rvEpoxy = binding.rvEpoxy
        rvEpoxy.setControllerAndBuildModels(epoxyController)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else ->
                super.onOptionsItemSelected(item)
        }
    }
}
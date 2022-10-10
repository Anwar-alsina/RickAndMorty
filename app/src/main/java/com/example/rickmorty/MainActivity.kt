package com.example.rickmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        mSharedViewModel.characterByIdLiveData.observe(this){response ->

            epoxyController.characterResponse = response

            if (response == null){
                Toast.makeText(
                    this@MainActivity,
                    "Unsuccessful network request",
                    Toast.LENGTH_LONG).show()
                return@observe
            }
        }

        mSharedViewModel.refreshCharacter(54)
        val rvEpoxy = binding.rvEpoxy
        rvEpoxy.setControllerAndBuildModels(epoxyController)
        }
}
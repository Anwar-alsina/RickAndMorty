package com.example.rickmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.rickmorty.databinding.ActivityMainBinding
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val mSharedViewModel by lazy {
        ViewModelProvider(this)[SharedViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mSharedViewModel.refreshCharacter(54)
        mSharedViewModel.characterByIdLiveData.observe(this){response ->
            if (response == null){
                Toast.makeText(this@MainActivity, "Unsuccessful network request", Toast.LENGTH_LONG).show()
                return@observe
            }
            val name = response.name

            binding.tvName.text = name
            binding.tvOriginDesc.text = response.origin.name
            binding.tvSpeciesDesc.text = response.species
            binding.tvStatus.text = response.status

            if (response.gender.equals("male", ignoreCase = true)){
                binding.ivGender.setImageResource(R.drawable.mars)
            }else{
                binding.ivGender.setImageResource(R.drawable.femenine)
            }

            Picasso.get().load(response.image).into(binding.ivPerson);

        }

        }
}
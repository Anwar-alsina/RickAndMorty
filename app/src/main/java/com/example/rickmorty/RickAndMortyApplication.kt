package com.example.rickmorty

import android.app.Application
import android.content.Context

class RickAndMortyApplication: Application() {

    companion object{
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}
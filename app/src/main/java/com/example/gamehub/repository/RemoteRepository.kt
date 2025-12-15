package com.example.gamehub.repository

import com.example.gamehub.model.RemoteGame
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteRepository {

    private val api: ApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api-wikigames.onrender.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(ApiService::class.java)
    }

    // Función que obtiene los juegos desde la API
    suspend fun WikiGamesRepository(): List<RemoteGame> {
        return api.getGames()
    }
}

package com.example.gamehub.repository

import com.example.gamehub.model.RemoteGame
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteRepository {

    private val api: ApiService

    init {
        val retrofit = Retrofit.Builder()
            // Tu URL base en Render (asegúrate de que termine en /)
            .baseUrl("https://api-wikigames.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(ApiService::class.java)
    }

    // --- Funciones CRUD ---

    suspend fun getAllGames(): List<RemoteGame> {
        return api.getGames()
    }

    suspend fun createGame(game: RemoteGame): RemoteGame {
        return api.createGame(game)
    }

    suspend fun updateGame(id: String, game: RemoteGame): RemoteGame {
        return api.updateGame(id, game)
    }

    suspend fun deleteGame(id: String) {
        api.deleteGame(id)
    }
}
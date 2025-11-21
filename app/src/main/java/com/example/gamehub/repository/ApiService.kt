package com.example.gamehub.repository

import com.example.gamehub.model.RemoteGame
import retrofit2.http.GET

interface ApiService {

    // API pública gratis de videojuegos
    // https://www.freetogame.com/api/games
    @GET("games")
    suspend fun getGames(): List<RemoteGame>
}

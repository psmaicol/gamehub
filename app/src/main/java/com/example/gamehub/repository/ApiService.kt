package com.example.gamehub.repository

import com.example.gamehub.model.RemoteGame
import retrofit2.http.GET

interface ApiService {

    // API pública gratis de videojuegos
    // https://www.freetogame.com/api/games
    
    // @GET indica que haremos una petición HTTP tipo GET al endpoint "games"
    // Es una función suspendida (suspend) para ejecutarse en una corrutina sin bloquear la UI
    @GET("games")
    suspend fun getGames(): List<RemoteGame>
}
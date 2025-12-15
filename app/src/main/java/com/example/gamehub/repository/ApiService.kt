package com.example.gamehub.repository

import com.example.gamehub.model.RemoteGame
import retrofit2.http.GET

interface ApiService {
    @GET("api/juegos") // <-- endpoint correcto
    suspend fun getGames(): List<RemoteGame>
}

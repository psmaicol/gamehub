package com.example.gamehub.repository

import com.example.gamehub.model.RemoteGame
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    // 1. LEER (Read)
    @GET("api/juegos")
    suspend fun getGames(): List<RemoteGame>

    // 2. CREAR (Create)
    @POST("api/juegos")
    suspend fun createGame(@Body game: RemoteGame): RemoteGame

    // 3. ACTUALIZAR (Update)
    @PUT("api/juegos/{id}")
    suspend fun updateGame(@Path("id") id: String, @Body game: RemoteGame): RemoteGame

    // 4. BORRAR (Delete)
    @DELETE("api/juegos/{id}")
    suspend fun deleteGame(@Path("id") id: String): Response<Unit>
}
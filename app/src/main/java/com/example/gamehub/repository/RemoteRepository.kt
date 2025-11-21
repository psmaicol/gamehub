package com.example.gamehub.repository

class RemoteRepository {

    suspend fun fetchRemoteGames() =
        RetrofitClien.api.getGames()
}

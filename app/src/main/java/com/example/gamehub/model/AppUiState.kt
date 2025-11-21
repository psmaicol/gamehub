package com.example.gamehub.model

data class AppUiState(
    val userEmail: String? = null,
    val loggedIn: Boolean = false,
    val games: List<GameItem> = emptyList(),

    // NUEVO: lista de juegos online desde API
    val remoteGames: List<RemoteGame> = emptyList()
)

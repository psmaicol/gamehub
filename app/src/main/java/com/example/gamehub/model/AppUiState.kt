package com.example.gamehub.model

data class AppUiState(
    val userEmail: String? = null,
    val loggedIn: Boolean = false,
    val games: List<GameItem> = emptyList()
)
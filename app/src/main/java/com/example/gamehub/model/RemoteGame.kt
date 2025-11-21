package com.example.gamehub.model

// Esto representa un juego que viene desde INTERNET (API)
data class RemoteGame(
    val id: Int,
    val title: String,
    val thumbnail: String,
    val short_description: String?
)

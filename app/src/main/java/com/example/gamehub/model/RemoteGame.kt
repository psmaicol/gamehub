package com.example.gamehub.model

import com.google.gson.annotations.SerializedName

// Modelo principal que coincide con tu API
data class RemoteGame(
    val id: String? = null, // Puede ser nulo al crear (el servidor lo genera)
    val title: String,
    val description: String?,
    val images: ImageContainer?
)

// Clase auxiliar para leer el objeto "images": { "cover": "url" }
data class ImageContainer(
    val cover: String?
)
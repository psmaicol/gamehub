package com.example.gamehub.model

import androidx.room.Entity
import androidx.room.PrimaryKey

// @Entity indica que esta clase representa una tabla en la base de datos SQLite (Room)
@Entity(tableName = "games")
data class GameItem(
    val name: String,        // Nombre del juego
    val description: String, // Descripción del juego
    val imageUri: String? = null, // URI de la imagen guardada como String
    
    // @PrimaryKey define la clave primaria. autoGenerate = true hace que el ID se incremente solo.
    @PrimaryKey(autoGenerate = true) val id: Long = 0
)
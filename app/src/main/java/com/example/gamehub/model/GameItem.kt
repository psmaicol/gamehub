package com.example.gamehub.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "games")
data class GameItem(
    val name: String,
    val description: String,
    val imageUri: String? = null,
    @PrimaryKey(autoGenerate = true) val id: Long = 0
)

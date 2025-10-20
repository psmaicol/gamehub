package com.example.gamehub.repository


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.gamehub.model.GameItem

@Dao
interface GameDao {
    @Query("SELECT * FROM games ORDER BY id DESC")
    suspend fun getAll(): List<GameItem>

    @Insert
    suspend fun insert(game: GameItem)
}
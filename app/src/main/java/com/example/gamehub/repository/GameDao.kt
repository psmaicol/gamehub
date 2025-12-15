package com.example.gamehub.repository


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.example.gamehub.model.GameItem

@Dao
interface GameDao {

    @Insert
    suspend fun insert(game: GameItem)

    @Query("SELECT * FROM games")
    suspend fun getAll(): List<GameItem>

    @Update
    suspend fun update(game: GameItem)

    @Delete
    suspend fun delete(game: GameItem)
}

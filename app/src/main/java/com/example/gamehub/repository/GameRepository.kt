package com.example.gamehub.repository


import com.example.gamehub.model.GameItem

class GameRepository private constructor( val dao: GameDao) {
    suspend fun getAllGames(): List<GameItem> = dao.getAll()
    suspend fun insertGame(game: GameItem) = dao.insert(game)

    companion object {
        @Volatile  var INSTANCE: GameRepository? = null

        fun getInstance(): GameRepository = INSTANCE ?: throw IllegalStateException("Repository not initialized. Call initWithDao from Application")

        fun initWithDao(dao: GameDao) {
            INSTANCE = GameRepository(dao)
        }
    }
}
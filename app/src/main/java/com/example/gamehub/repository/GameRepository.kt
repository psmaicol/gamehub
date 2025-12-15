package com.example.gamehub.repository

import com.example.gamehub.model.GameItem

class GameRepository private constructor() {

    private lateinit var dao: GameDao

    fun init(dao: GameDao) {
        this.dao = dao
    }

    suspend fun insertGame(game: GameItem) {
        dao.insert(game)
    }

    suspend fun getAllGames(): List<GameItem> {
        return dao.getAll()
    }

    suspend fun updateGame(game: GameItem) {
        dao.update(game)
    }

    suspend fun deleteGame(game: GameItem) {
        dao.delete(game)
    }

    companion object {
        private var INSTANCE: GameRepository? = null

        fun initWithDao(dao: GameDao) {
            if (INSTANCE == null) {
                INSTANCE = GameRepository().apply { init(dao) }
            }
        }

        fun getInstance(): GameRepository {
            return INSTANCE
                ?: throw IllegalStateException("GameRepository no inicializado")
        }
    }
}

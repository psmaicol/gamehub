package com.example.gamehub.repository

import com.example.gamehub.model.GameItem

// El repositorio abstrae el acceso a datos. Aquí usamos el DAO para operaciones locales.
class GameRepository private constructor( val dao: GameDao) {
    
    // Función suspendida para obtener todos los juegos de la BD
    suspend fun getAllGames(): List<GameItem> = dao.getAll()
    
    // Función suspendida para insertar un nuevo juego
    suspend fun insertGame(game: GameItem) = dao.insert(game)

    // Patrón Singleton para asegurar que solo exista una instancia del repositorio en toda la app
    companion object {
        @Volatile  var INSTANCE: GameRepository? = null

        // Obtiene la instancia actual o lanza error si no se ha inicializado
        fun getInstance(): GameRepository = INSTANCE ?: throw IllegalStateException("Repository not initialized. Call initWithDao from Application")

        // Inicializa el repositorio con el DAO (se llama desde App.kt)
        fun initWithDao(dao: GameDao) {
            INSTANCE = GameRepository(dao)
        }
    }
}
package com.example.gamehub


import android.app.Application
import androidx.room.Room
import com.example.gamehub.repository.GameDatabase
import com.example.gamehub.repository.GameRepository

// Clase App: Se ejecuta antes que cualquier Activity. Ideal para inicializar la BD.
class App : Application() {

    lateinit var repository: GameRepository
        private set

    override fun onCreate() {
        super.onCreate()

        // Construcción de la base de datos Room
        val db = Room.databaseBuilder(
            applicationContext,
            GameDatabase::class.java,
            "game_db"
        ).build()

        // Inicializamos el repository con el DAO generado por la base de datos
        GameRepository.initWithDao(db.gameDao())
        repository = GameRepository.getInstance()
    }
}
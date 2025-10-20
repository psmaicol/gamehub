package com.example.gamehub


import android.app.Application
import androidx.room.Room
import com.example.gamehub.repository.GameDatabase
import com.example.gamehub.repository.GameRepository

class App : Application() {

    lateinit var repository: GameRepository
        private set

    override fun onCreate() {
        super.onCreate()

        val db = Room.databaseBuilder(
            applicationContext,
            GameDatabase::class.java,
            "game_db"
        ).build()

        // Inicializamos el repository
        GameRepository.initWithDao(db.gameDao())
        repository = GameRepository.getInstance()
    }
}
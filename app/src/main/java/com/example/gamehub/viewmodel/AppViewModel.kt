package com.example.gamehub.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamehub.model.AppUiState
import com.example.gamehub.model.GameItem
import com.example.gamehub.model.RemoteGame
import com.example.gamehub.model.ImageContainer
import com.example.gamehub.repository.GameRepository
import com.example.gamehub.repository.RemoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AppViewModel(repository1: GameRepository) : ViewModel() {

    private val repository = GameRepository.getInstance()
    private val remoteRepository = RemoteRepository()

    private val _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState

    // ==========================================
    // 1. FUNCIONES LOCALES (ROOM - Base de datos del celular)
    // ==========================================

    fun login(email: String, pass: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            onError("Email inválido"); return
        }
        if (pass.length < 4) {
            onError("La contraseña debe ser mayor a 4 caracteres"); return
        }
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(userEmail = email, loggedIn = true)
            onSuccess()
        }
    }

    fun loadGames() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(games = repository.getAllGames())
        }
    }

    fun addGame(name: String, description: String, imageUri: Uri?) {
        viewModelScope.launch {
            val game = GameItem(name = name, description = description, imageUri = imageUri?.toString())
            repository.insertGame(game)
            loadGames()
        }
    }

    fun updateGame(game: GameItem) {
        viewModelScope.launch {
            repository.updateGame(game)
            loadGames()
        }
    }

    fun deleteGame(game: GameItem) {
        viewModelScope.launch {
            repository.deleteGame(game)
            loadGames()
        }
    }

    // ==========================================
    // 2. FUNCIONES REMOTAS (API - Internet)
    // ==========================================

    // LEER (GET)
    fun loadRemoteGames() {
        viewModelScope.launch {
            try {
                val onlineGames = remoteRepository.getAllGames()
                _uiState.value = _uiState.value.copy(remoteGames = onlineGames)
            } catch (e: Exception) {
                println("Error API: ${e.message}")
            }
        }
    }

    // CREAR (POST) - Esta función faltaba o no la tenías
    fun addRemoteGame(title: String, description: String, imageUrl: String) {
        viewModelScope.launch {
            try {
                val newGame = RemoteGame(
                    title = title,
                    description = description,
                    images = ImageContainer(cover = imageUrl)
                )
                remoteRepository.createGame(newGame)
                loadRemoteGames() // Recargamos la lista para ver el cambio
            } catch (e: Exception) {
                println("Error al crear: ${e.message}")
            }
        }
    }

    // BORRAR (DELETE) - ¡Esta es la que te daba error!
    fun deleteRemoteGame(id: String) {
        viewModelScope.launch {
            try {
                remoteRepository.deleteGame(id)
                loadRemoteGames() // Recargamos la lista
            } catch (e: Exception) {
                println("Error al borrar: ${e.message}")
            }
        }
    }

    // ACTUALIZAR (PUT)
    fun updateRemoteGame(id: String, title: String, description: String, imageUrl: String) {
        viewModelScope.launch {
            try {
                val gameToUpdate = RemoteGame(
                    id = id,
                    title = title,
                    description = description,
                    images = ImageContainer(cover = imageUrl)
                )
                remoteRepository.updateGame(id, gameToUpdate)
                loadRemoteGames()
            } catch (e: Exception) {
                println("Error al actualizar: ${e.message}")
            }
        }
    }

    init {
        loadGames() // Carga inicial local
        loadRemoteGames() // Carga inicial remota
    }
}
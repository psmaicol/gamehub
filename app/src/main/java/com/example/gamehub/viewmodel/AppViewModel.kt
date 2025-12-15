package com.example.gamehub.viewmodel

import android.net.Uri
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamehub.model.AppUiState
import com.example.gamehub.model.GameItem
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

    fun login(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            onError("Email inválido"); return
        }
        if (password.length < 4) {
            onError("La contraseña debe tener al menos 4 caracteres"); return
        }

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(userEmail = email, loggedIn = true)
            onSuccess()
        }
    }

    fun addGame(name: String, description: String, imageUri: Uri?) {
        viewModelScope.launch {
            val game = GameItem(
                name = name,
                description = description,
                imageUri = imageUri?.toString()
            )
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

    fun loadGames() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                games = repository.getAllGames()
            )
        }
    }

    fun loadRemoteGames() {
        println("ENTRÓ A loadRemoteGames()")

        viewModelScope.launch {
            try {
                val onlineGames = remoteRepository.WikiGamesRepository()
                println("Juegos recibidos: ${onlineGames.size}")
                _uiState.value = _uiState.value.copy(remoteGames = onlineGames)
            } catch (e: Exception) {
                println("Error API: ${e.message}")
            }
        }
    }




    init {
        loadGames()
    }
}

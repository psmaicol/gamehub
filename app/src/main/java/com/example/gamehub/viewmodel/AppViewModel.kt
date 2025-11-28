package com.example.gamehub.viewmodel

import android.net.Uri
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

    // Instancias de los repositorios (Local y Remoto)
    private val repository = GameRepository.getInstance()
    private val remoteRepository = RemoteRepository()

    // StateFlow mantiene el estado actual de la UI. Es reactivo (la UI se actualiza cuando esto cambia).
    private val _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState

    // Lógica para iniciar sesión
    fun login(email: String, password: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        // Validación 1: Formato de email
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            onError("Email inválido"); return
        }
        // Validación 2: Longitud de contraseña
        if (password.length < 4) {
            onError("La contraseña debe tener al menos 4 caracteres"); return
        }

        // Si pasa las validaciones, actualizamos el estado a "logueado"
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(userEmail = email, loggedIn = true)
            onSuccess() // Callback para navegar a la siguiente pantalla
        }
    }

    // Agregar un juego a la base de datos local (Room)
    fun addGame(name: String, description: String, imageUri: Uri?) {
        viewModelScope.launch { // Corrutina para operación en segundo plano
            val game = GameItem(name = name, description = description, imageUri = imageUri?.toString())
            repository.insertGame(game)
            // Después de insertar, recargamos la lista para actualizar la UI
            _uiState.value = _uiState.value.copy(games = repository.getAllGames())
        }
    }

    // Cargar juegos guardados localmente
    fun loadGames() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(games = repository.getAllGames())
        }
    }

    // NUEVO: cargar juegos desde API (Internet)
    fun loadRemoteGames() {
        viewModelScope.launch {
            try {
                // Llamada de red a través de Retrofit
                val onlineGames = remoteRepository.fetchRemoteGames()
                // Actualizamos el estado con la lista de juegos remotos
                _uiState.value = _uiState.value.copy(remoteGames = onlineGames)
            } catch (e: Exception) {
                // Manejo básico de errores (imprimir en consola)
                println("Error al cargar API: ${e.message}")
            }
        }
    }

    // Bloque init: se ejecuta al crear el ViewModel. Carga los juegos locales al inicio.
    init { loadGames() }
}
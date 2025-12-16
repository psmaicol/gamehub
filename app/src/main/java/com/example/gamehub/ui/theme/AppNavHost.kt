package com.example.gamehub.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gamehub.viewmodel.AppViewModel

@Composable
fun AppNavHost(viewModel: AppViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {

        // --- Pantallas de Inicio ---
        composable("login") { LoginScreen(navController, viewModel) }
        composable("home") { HomeScreen(navController, viewModel) }
        composable("about") { AboutScreen() }

        // --- Rutas Locales (Base de Datos) ---
        composable("add") { AddGameScreen(navController, viewModel) }

        composable("detail/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")
            DetailScreen(navController, viewModel, id)
        }

        composable("edit/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")
            val game = viewModel.uiState.collectAsState().value.games
                .firstOrNull { it.id.toString() == id }

            if (game != null) {
                EditGameScreen(navController, viewModel, game)
            }
        }

        // --- Rutas Online (API) ---
        composable("remote") { RemoteGamesScreen(navController, viewModel) }

        // Esta es la ruta para "Agregar juego online" (evita el crash)
        composable("add_remote_game") { AddRemoteGameScreen(navController, viewModel) }
    }
}
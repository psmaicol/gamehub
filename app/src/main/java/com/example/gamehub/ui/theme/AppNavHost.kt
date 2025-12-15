package com.example.gamehub.ui.theme
import androidx.compose.runtime.collectAsState
import com.example.gamehub.ui.theme.EditGameScreen

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gamehub.ui.HomeScreen


@Composable
fun AppNavHost(viewModel: com.example.gamehub.viewmodel.AppViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController, viewModel) }
        composable("home") { HomeScreen(navController, viewModel) }
        composable("add") { AddGameScreen(navController, viewModel) }
        composable("detail/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")
            DetailScreen(navController, viewModel, id)
        }
        composable("about") { AboutScreen() }
        composable("login") { LoginScreen(navController, viewModel) }
        composable("home") { HomeScreen(navController, viewModel) }
        composable("add") { AddGameScreen(navController, viewModel) }
        composable("detail/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")
            DetailScreen(navController, viewModel, id)
        }
        composable("about") { AboutScreen() }

        // NUEVO
        composable("remote") { RemoteGamesScreen(navController, viewModel) }
        composable("edit/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")
            val game = viewModel.uiState.collectAsState().value.games
                .first { it.id.toString() == id }

            EditGameScreen(navController, viewModel, game)
        }

    }


}


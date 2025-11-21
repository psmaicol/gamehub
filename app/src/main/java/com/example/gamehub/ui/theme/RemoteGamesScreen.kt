package com.example.gamehub.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.gamehub.viewmodel.AppViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RemoteGamesScreen(navController: NavController, viewModel: AppViewModel) {
    val state by viewModel.uiState.collectAsState()

    // Cuando entra a la pantalla, carga la API
    LaunchedEffect(Unit) {
        viewModel.loadRemoteGames()
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Juegos Online") })
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(state.remoteGames) { game ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Row(modifier = Modifier.padding(16.dp)) {
                        Image(
                            painter = rememberAsyncImagePainter(game.thumbnail),
                            contentDescription = null,
                            modifier = Modifier.size(80.dp),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(game.title, style = MaterialTheme.typography.titleMedium)
                    }
                }
            }
        }
    }
}

package com.example.gamehub.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.gamehub.viewmodel.AppViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RemoteGamesScreen(navController: NavController, viewModel: AppViewModel) {
    val state by viewModel.uiState.collectAsState()

    // Carga la API cuando entra a la pantalla
    LaunchedEffect(Unit) {
        viewModel.loadRemoteGames()
    }

    // Gradiente de fondo
    val gradient = Brush.verticalGradient(
        listOf(
            Color(0xFF1565C0),
            Color(0xFF2196F3),
            MaterialTheme.colorScheme.background
        )
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Juegos Online",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF0D47A1),
                    titleContentColor = Color.White
                )
            )
        },
        floatingActionButton = {
            // Botón flotante para agregar juego a la API
            FloatingActionButton(
                onClick = { navController.navigate("add_remote_game") }, // Asegúrate de tener esta ruta o crear una pantalla
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Text("+", style = MaterialTheme.typography.headlineMedium, color = Color.White)
            }
        }
    ) { padding ->

        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(gradient)
        ) {

            if (state.remoteGames.isEmpty()) {
                // Mensaje si no hay juegos
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(color = Color.White)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        "Cargando juegos o lista vacía...",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                }
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(state.remoteGames) { game ->

                        // CORRECCIÓN PRINCIPAL:
                        // Accedemos a la imagen de forma segura usando la nueva estructura
                        val imageUrl = game.images?.cover ?: "https://via.placeholder.com/150"

                        ElevatedCard(
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier.padding(16.dp)
                            ) {

                                Image(
                                    painter = rememberAsyncImagePainter(imageUrl),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(90.dp)
                                        .clip(RoundedCornerShape(12.dp)),
                                    contentScale = ContentScale.Crop
                                )

                                Spacer(modifier = Modifier.width(16.dp))

                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = game.title,
                                        style = MaterialTheme.typography.titleLarge,
                                        color = Color.Black
                                    )

                                    Spacer(modifier = Modifier.height(6.dp))

                                    Text(
                                        text = game.description ?: "Sin descripción",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = Color.DarkGray,
                                        maxLines = 2
                                    )

                                    // Botones de acción (Editar / Borrar) para el CRUD
                                    Row(modifier = Modifier.padding(top = 8.dp)) {
                                        IconButton(
                                            onClick = {
                                                // Aquí llamarías a viewModel.deleteRemoteGame(game.id!!)
                                                game.id?.let { viewModel.deleteRemoteGame(it) }
                                            },
                                            modifier = Modifier.size(24.dp)
                                        ) {
                                            Icon(
                                                Icons.Default.Delete,
                                                contentDescription = "Borrar",
                                                tint = MaterialTheme.colorScheme.error
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
package com.example.gamehub.ui.theme



import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
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
fun DetailScreen(navController: NavController, viewModel: AppViewModel, id: String?) {

    val game = viewModel.uiState.collectAsState().value.games
        .firstOrNull { it.id.toString() == id }

    val headerHeight = 260.dp

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = "Volver",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        }
    ) { padding ->

        game?.let {

            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
            ) {

                // =========  HEADER CON IMAGEN + DEGRADADO  ===========
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(headerHeight)
                ) {

                    Image(
                        painter = rememberAsyncImagePainter(it.imageUri),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )

                    // Degradado para que el texto no choque con la imagen
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    listOf(
                                        Color.Transparent,
                                        MaterialTheme.colorScheme.surface.copy(alpha = 0.90f)
                                    )
                                )
                            )
                    )
                }

                // =========  TARJETA CON INFORMACIÓN  ===========
                ElevatedCard(
                    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                    modifier = Modifier
                        .offset(y = (-24).dp)
                        .fillMaxWidth()
                ) {

                    Column(
                        modifier = Modifier
                            .padding(24.dp)
                    ) {

                        Text(
                            text = it.name,
                            style = MaterialTheme.typography.headlineMedium
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = it.description,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}

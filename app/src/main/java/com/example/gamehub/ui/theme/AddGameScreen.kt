package com.example.gamehub.ui.theme

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.gamehub.viewmodel.AppViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddGameScreen(navController: NavController, viewModel: AppViewModel) {

    var name by rememberSaveable { mutableStateOf("") }
    var desc by rememberSaveable { mutableStateOf("") }
    var imageUri by rememberSaveable { mutableStateOf<Uri?>(null) }
    var error by remember { mutableStateOf<String?>(null) }

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        if (uri != null) imageUri = uri
    }

    // Fondo con gradiente suave
    val gradient = Brush.verticalGradient(
        listOf(
            MaterialTheme.colorScheme.primary.copy(alpha = 0.30f),
            MaterialTheme.colorScheme.background
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient),
        contentAlignment = Alignment.TopCenter
    ) {

        ElevatedCard(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(24.dp)
        ) {

            Column(
                modifier = Modifier
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    "Agregar Juego",
                    style = MaterialTheme.typography.headlineSmall
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Imagen seleccionada (previsualización)
                if (imageUri != null) {
                    AsyncImage(
                        model = imageUri,
                        contentDescription = null,
                        modifier = Modifier
                            .size(140.dp)
                            .clip(RoundedCornerShape(16.dp))
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }

                // Botón para seleccionar imagen
                OutlinedButton(
                    onClick = { launcher.launch("image/*") },
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Seleccionar imagen")
                }

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nombre del juego") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = desc,
                    onValueChange = { desc = it },
                    label = { Text("Descripción") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        if (name.isBlank()) {
                            error = "El nombre es obligatorio"
                            return@Button
                        }
                        viewModel.addGame(name, desc, imageUri)
                        navController.popBackStack()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Guardar juego")
                }

                error?.let {
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(it, color = MaterialTheme.colorScheme.error)
                }
            }
        }
    }
}

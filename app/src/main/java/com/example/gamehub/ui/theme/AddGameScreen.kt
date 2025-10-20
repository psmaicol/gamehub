package com.example.gamehub.ui.theme
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gamehub.viewmodel.AppViewModel

@Composable
fun AddGameScreen(navController: NavController, viewModel: AppViewModel) {
    var name by rememberSaveable { mutableStateOf("") }
    var desc by rememberSaveable { mutableStateOf("") }
    var imageUri by rememberSaveable { mutableStateOf<Uri?>(null) }
    var error by remember { mutableStateOf<String?>(null) }

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri -> if (uri != null) imageUri = uri }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Nombre del juego") }, singleLine = true)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = desc, onValueChange = { desc = it }, label = { Text("Descripción") })
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { launcher.launch("image/*") }) { Text("Seleccionar imagen") }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            if (name.isBlank()) { error = "El nombre es obligatorio"; return@Button }
            viewModel.addGame(name, desc, imageUri)
            navController.popBackStack()
        }) { Text("Guardar juego") }

        error?.let { Text(it, color = MaterialTheme.colorScheme.error) }
    }
}

package com.example.gamehub.ui.theme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AboutScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(24.dp))
        Text("VideoGameApp")
        Spacer(modifier = Modifier.height(8.dp))
        Text("App básica para registrar tus juegos favoritos.\nFunciona con Compose, MVVM y Room.")
    }
}
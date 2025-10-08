package com.example.stuffies_proyect_grupo_6.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeAdaptive(
    onProfile: () -> Unit,
    onSettings: () -> Unit
) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("HOME", style = MaterialTheme.typography.headlineMedium)
            Button(onClick = onProfile) { Text("Ir a Perfil") }
            Button(onClick = onSettings) { Text("Ir a Settings") }
        }
    }
}

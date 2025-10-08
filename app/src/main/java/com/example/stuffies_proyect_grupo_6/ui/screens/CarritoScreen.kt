package com.example.stuffies_proyect_grupo_6.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CarritoScreen(
    onBack: () -> Unit = {},
    onHome: () -> Unit = {},
    onContinuar: () -> Unit = {}
) {
    ScreenScaffold(title = "Carrito", onBack = onBack, onHome = onHome) {
        Button(onClick = onContinuar) { Text("Pagar / Continuar") }
    }
}

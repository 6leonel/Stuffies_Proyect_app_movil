package com.example.stuffies_proyect_grupo_6.ui.screens

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@Composable
fun LoginScreen(
    onBack: () -> Unit = {},
    onHome: () -> Unit = {}
) {
    ScreenScaffold(title = "Login", onBack = onBack, onHome = onHome)
}

package com.example.stuffies_proyect_grupo_6.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ProductoDetalleScreen(id: Int) {
    ScreenScaffold(
        title = "Detalle producto #$id",
        onBack = {},
        onHome = {}
    ) {
        Text(text = "Aquí va la info del producto $id")
    }
}

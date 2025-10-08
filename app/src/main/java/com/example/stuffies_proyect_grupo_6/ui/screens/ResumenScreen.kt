package com.example.stuffies_proyect_grupo_6.ui.screens

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stuffies_proyect_grupo_6.viewmodel.NavViewModel // ⚠️ cambia el tipo/nombre si tu VM es distinto

@Composable
fun ResumenScreen(
    vm: NavViewModel = viewModel(),   // ⚠️ ajusta el tipo si tu VM se llama diferente
    onBack: () -> Unit = {},
    onHome: () -> Unit = {}
) {
    ScreenScaffold(title = "Resumen", onBack = onBack, onHome = onHome) {
        // Ejemplo de uso del vm si quieres:
        // Text("Estado: ${vm.algo}")
    }
}

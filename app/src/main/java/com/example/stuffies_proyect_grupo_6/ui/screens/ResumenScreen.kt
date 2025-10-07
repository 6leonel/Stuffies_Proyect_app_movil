package com.example.stuffies_proyect_grupo_6.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.stuffies_proyect_grupo_6.viewmodel.UsuarioViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResumenScreen(
    vm: UsuarioViewModel,
    onBack: () -> Unit,
    onIrHome: () -> Unit
) {
    val ui by vm.uiState.collectAsState()

    Scaffold(topBar = { TopAppBar(title = { Text("Resumen de datos") }) }) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Nombre: ${ui.nombre}")
            Text("Correo: ${ui.correo}")
            Text("Dirección: ${ui.direccion}")
            Text("Acepta Términos: ${if (ui.aceptaTerminos) "Sí" else "No"}")

            Spacer(Modifier.height(16.dp))
            Button(onClick = onBack, modifier = Modifier.fillMaxWidth()) { Text("Volver") }
            Button(onClick = onIrHome, modifier = Modifier.fillMaxWidth()) { Text("Ir a Home") }
        }
    }
}

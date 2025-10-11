package com.example.stuffies_proyect_grupo_6.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.stuffies_proyect_grupo_6.viewmodel.UsuarioViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResumenScreen(
    vm: UsuarioViewModel,      // 👈 el mismo VM compartido
    onBack: () -> Unit
) {
    val estado by vm.estado.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Resumen") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { inner ->
        Column(Modifier.padding(inner).padding(16.dp)) {
            Text("Nombre: ${estado.nombre}")
            Text("Correo: ${estado.correo}")
            Text("Dirección: ${estado.direccion}")
            Text("Acepta términos: ${if (estado.aceptaTerminos) "Sí" else "No"}")
        }
    }
}

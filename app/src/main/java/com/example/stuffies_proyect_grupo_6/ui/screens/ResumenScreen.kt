@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example.stuffies_proyect_grupo_6.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.stuffies_proyect_grupo_6.viewmodel.UsuarioViewModel

@Composable
fun ResumenScreen(
    vm: UsuarioViewModel,           // por ahora no lo usamos; lo dejo para mantener tu firma
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Resumen de registro") },
                navigationIcon = {
                    // Si no tienes íconos, dejamos vacío para no requerir material-icons
                    IconButton(onClick = onBack) { Text("<") }
                }
            )
        }
    ) { inner ->
        Column(
            modifier = Modifier
                .padding(inner)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            ElevatedCard {
                Column(Modifier.padding(16.dp)) {
                    Text("Datos ingresados", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                    Spacer(Modifier.height(6.dp))
                    Text("Nombre: (pendiente de enlazar con VM)")
                    Text("Correo: (pendiente de enlazar con VM)")
                    Text("Contraseña: (pendiente)")
                }
            }

            Spacer(Modifier.height(12.dp))
            Button(
                onClick = { /* aquí guardarías en DataStore/Room o llamar a tu repo */ },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Confirmar y crear cuenta") }

            Spacer(Modifier.height(8.dp))
            OutlinedButton(
                onClick = onBack,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Editar") }
        }
    }
}

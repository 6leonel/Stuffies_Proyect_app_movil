@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example.stuffies_proyect_grupo_6.ui.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.stuffies_proyect_grupo_6.navigation.Route
import com.example.stuffies_proyect_grupo_6.viewmodel.UsuarioViewModel

@Composable
fun ResumenScreen(
    navController: NavController,
    vm: UsuarioViewModel
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Resumen registro") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            // --- Contenido principal (ajusta a tu gusto) ---
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "¡Registro completado!",
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = "Tus datos se han guardado correctamente.",
                    style = MaterialTheme.typography.bodyMedium
                )
                // Si quieres mostrar datos reales desde el VM, descomenta y adapta:
                /*
                val estado by vm.estado.collectAsState()
                Text("Nombre: ${estado.nombre}")
                Text("Correo: ${estado.correo}")
                */
            }

            // --- Fila de botones Back / Home ---
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Botón BACK
                OutlinedButton(
                    onClick = {
                        Log.d("ResumenScreen", "Back clickeado")
                        navController.popBackStack()
                    },
                    shape = RoundedCornerShape(50),
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color(0xFFB197FC)
                    ),
                    border = BorderStroke(1.dp, Color(0xFFB197FC))
                ) {
                    Text("Back")
                }

                // Botón HOME
                Button(
                    onClick = {
                        Log.d("ResumenScreen", "Home clickeado")
                        navController.navigate(Route.Home.path) {
                            popUpTo(navController.graph.startDestinationId) {
                                inclusive = false
                            }
                            launchSingleTop = true
                        }
                    },
                    shape = RoundedCornerShape(50),
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF7C3AED),
                        contentColor = Color.White
                    )
                ) {
                    Text("Home")
                }
            }
        }
    }
}

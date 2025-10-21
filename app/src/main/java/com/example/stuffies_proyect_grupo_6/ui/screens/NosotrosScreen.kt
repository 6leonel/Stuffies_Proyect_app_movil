@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example.stuffies_proyect_grupo_6.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun NosotrosScreen() {
    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text("Nosotros") }) }
    ) { inner ->
        Column(
            modifier = Modifier
                .padding(inner)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Text(
                "Stuffies — Ropa urbana con identidad",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(8.dp))
            Text(
                "Somos una marca creada por estudiantes, inspirada en el fútbol clásico y la cultura street. " +
                        "Diseñamos prendas cómodas, duraderas y con estilo propio.",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(Modifier.height(16.dp))

            ElevatedCard {
                Column(Modifier.padding(16.dp)) {
                    Text("Misión", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                    Spacer(Modifier.height(6.dp))
                    Text("Impulsar el estilo personal con prendas de calidad y precio justo.")
                }
            }
            Spacer(Modifier.height(12.dp))
            ElevatedCard {
                Column(Modifier.padding(16.dp)) {
                    Text("Visión", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                    Spacer(Modifier.height(6.dp))
                    Text("Ser la marca urbana favorita en Chile por diseño, cercanía y servicio.")
                }
            }
        }
    }
}

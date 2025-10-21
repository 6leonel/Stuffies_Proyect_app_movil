@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example.stuffies_proyect_grupo_6.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

private data class BlogPost(
    val id: String,
    val titulo: String,
    val fecha: String,
    val extracto: String
)

@Composable
fun BlogsScreen() {
    val posts = listOf(
        BlogPost("b001","Streetwear 2025: capas y siluetas amplias","2025-09-30",
            "Cómo combinar polerones oversize con jeans rectos y zapatillas retro."),
        BlogPost("b002","Guía de tallas Stuffies","2025-09-15",
            "Aprende a medirte y elegir corte según cada prenda."),
        BlogPost("b003","Cuidados de tus prendas favoritas","2025-08-28",
            "Lava en frío y seca a la sombra para conservar colores vivos.")
    )

    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text("Blog") }) }
    ) { inner ->
        LazyColumn(
            modifier = Modifier
                .padding(inner)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            items(posts) { p ->
                ElevatedCard {
                    Column(Modifier.padding(16.dp)) {
                        Text(p.titulo, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                        Spacer(Modifier.height(4.dp))
                        Text(p.fecha, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.outline)
                        Spacer(Modifier.height(8.dp))
                        Text(p.extracto, style = MaterialTheme.typography.bodyMedium)
                        Spacer(Modifier.height(8.dp))
                        Text("Pronto más…", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.outline)
                    }
                }
                Spacer(Modifier.height(12.dp))
            }
        }
    }
}

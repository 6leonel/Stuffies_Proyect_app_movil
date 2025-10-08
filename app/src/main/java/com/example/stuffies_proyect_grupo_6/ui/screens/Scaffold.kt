package com.example.stuffies_proyect_grupo_6.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ScreenScaffold(
    title: String,
    onBack: () -> Unit = {},
    onHome: () -> Unit = {},
    backgroundColor: Color = Color.Transparent,   // 👈 nuevo
    content: @Composable ColumnScope.() -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)          // 👈 pinta el fondo
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = title, style = MaterialTheme.typography.titleLarge, color = Color.White)
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedButton(onClick = onBack) { Text("Back") }
                    Button(onClick = onHome) { Text("Home") }
                }
            }
            content()
        }
    }
}

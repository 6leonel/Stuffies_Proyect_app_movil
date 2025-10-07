package com.example.stuffies_proyect_grupo_6.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings

@Composable
fun SettingsScreen(
    onHome: () -> Unit,
    onProfile: () -> Unit
) {
    var selectedIndex by remember { mutableStateOf(1) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = selectedIndex == 0,
                    onClick = { selectedIndex = 0; onHome() },
                    icon = { Icon(Icons.Filled.Home, contentDescription = null) },
                    label = { Text("Inicio") }
                )
                NavigationBarItem(
                    selected = selectedIndex == 1,
                    onClick = { selectedIndex = 1 },
                    icon = { Icon(Icons.Filled.Settings, contentDescription = null) },
                    label = { Text("Ajustes") }
                )
                NavigationBarItem(
                    selected = selectedIndex == 2,
                    onClick = { selectedIndex = 2; onProfile() },
                    icon = { Icon(Icons.Filled.Person, contentDescription = null) },
                    label = { Text("Perfil") }
                )
            }
        }
    ) { innerPadding ->
        Text(
            text = "Pantalla de Ajustes",
            modifier = Modifier.padding(innerPadding),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example.stuffies_proyect_grupo_6.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.stuffies_proyect_grupo_6.navigation.Route
import com.example.stuffies_proyect_grupo_6.viewmodel.UsuarioViewModel

@Composable
fun RegistroScreen(
    navController: NavHostController,
    viewModel: UsuarioViewModel // por ahora no lo usamos para que compile
) {
    val nombre = remember { mutableStateOf("") }
    val email  = remember { mutableStateOf("") }
    val pass   = remember { mutableStateOf("") }
    val error  = remember { mutableStateOf<String?>(null) }

    fun validar(): Boolean {
        if (nombre.value.isBlank() || email.value.isBlank() || pass.value.isBlank()) {
            error.value = "Completa todos los campos."
            return false
        }
        if (!email.value.contains("@")) {
            error.value = "Correo inválido."
            return false
        }
        if (pass.value.length < 6) {
            error.value = "La contraseña debe tener al menos 6 caracteres."
            return false
        }
        return true
    }

    fun continuar() {
        error.value = null
        if (!validar()) return

        // Si luego quieres persistir en VM, aquí llamamos a tus métodos reales.
        // Por ahora solo navegamos para que compile y funcione el flujo.
        navController.navigate(Route.Resumen.path)
    }

    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text("Registro") }) }
    ) { inner ->
        Column(
            modifier = Modifier
                .padding(inner)
                .padding(16.dp)
        ) {
            if (error.value != null) {
                Text(text = error.value!!, color = androidx.compose.ui.graphics.Color.Red)
                Spacer(Modifier.height(8.dp))
            }

            OutlinedTextField(
                value = nombre.value, onValueChange = { nombre.value = it },
                label = { Text("Nombre") }, singleLine = true, modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = email.value, onValueChange = { email.value = it },
                label = { Text("Correo") }, singleLine = true, modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = pass.value, onValueChange = { pass.value = it },
                label = { Text("Contraseña") }, singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(12.dp))
            Button(
                onClick = { continuar() },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Continuar") }
        }
    }
}

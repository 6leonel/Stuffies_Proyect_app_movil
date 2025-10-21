@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example.stuffies_proyect_grupo_6.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen() {
    val ctx = LocalContext.current
    val email = remember { mutableStateOf("") }
    val pass = remember { mutableStateOf("") }
    val error = remember { mutableStateOf<String?>(null) }
    val loading = remember { mutableStateOf(false) }

    fun doLogin() {
        error.value = null
        if (email.value.isBlank() || pass.value.isBlank()) {
            error.value = "Completa todos los campos."
            return
        }
        if (!email.value.contains("@")) {
            error.value = "Formato de correo inválido."
            return
        }
        if (pass.value.length < 6) {
            error.value = "La contraseña debe tener al menos 6 caracteres."
            return
        }
        loading.value = true
        loading.value = false
        Toast.makeText(ctx, "¡Bienvenido!", Toast.LENGTH_SHORT).show()
        // Si luego quieres navegar, pásale NavController por parámetro y navega aquí.
    }

    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text("Iniciar sesión") }) }
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
                onClick = { doLogin() },
                enabled = !loading.value,
                modifier = Modifier.fillMaxWidth()
            ) { Text(if (loading.value) "Ingresando..." else "Entrar") }
        }
    }
}

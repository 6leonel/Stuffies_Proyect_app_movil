@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example.stuffies_proyect_grupo_6.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private val BlackBg   = Color(0xFF000000)
private val GrayText  = Color(0xFFBFBFBF)
private val GrayLine  = Color(0xFF1F2937)

@Composable
fun LoginScreen() {
    val snackbar = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    var id by remember { mutableStateOf("") }    // usuario o correo
    var pass by remember { mutableStateOf("") }
    var rememberMe by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }

    // 👇 NUEVO: usuario logeado
    var loggedInUser by remember { mutableStateOf<String?>(null) }

    fun validate(): String? {
        if (id.isBlank()) return "Ingresa tu usuario o correo."
        if (pass.length !in 4..10) return "La contraseña debe tener entre 4 y 10 caracteres."
        return null
    }

    suspend fun doLogin() {
        val err = validate()
        if (err != null) {
            snackbar.showSnackbar(err, withDismissAction = true)
            return
        }
        isLoading = true
        // Simulación de login
        delay(300)
        isLoading = false

        // 👇 Guardamos quién inició sesión
        loggedInUser = id

        snackbar.showSnackbar("¡Inicio de sesión correcto!", withDismissAction = true)
        // Aquí podrías navegar si luego le pasas un NavController
    }

    Scaffold(
        containerColor = BlackBg,
        contentColor = Color.White,
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = BlackBg,
                    titleContentColor = Color.White
                ),
                title = { Text("Iniciar sesión") }
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbar) }
    ) { inner ->
        Column(
            modifier = Modifier
                .padding(inner)
                .fillMaxSize()
                .background(BlackBg)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(Modifier.height(12.dp))

            Text(
                "Ingresa a tu cuenta de STUFFIES",
                color = GrayText,
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = id,
                onValueChange = { id = it },
                label = { Text("Usuario o correo electrónico") },
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = BlackBg,
                    focusedContainerColor = BlackBg,
                    unfocusedTextColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedLabelColor = GrayText,
                    focusedLabelColor = Color.White,
                    unfocusedBorderColor = GrayLine,
                    focusedBorderColor = Color.White,
                    cursorColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = pass,
                onValueChange = { pass = it },
                label = { Text("Contraseña") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = BlackBg,
                    focusedContainerColor = BlackBg,
                    unfocusedTextColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedLabelColor = GrayText,
                    focusedLabelColor = Color.White,
                    unfocusedBorderColor = GrayLine,
                    focusedBorderColor = Color.White,
                    cursorColor = Color.White
                ),
                supportingText = {
                    Text("4–10 caracteres", color = GrayText, style = MaterialTheme.typography.labelSmall)
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                    Checkbox(
                        checked = rememberMe,
                        onCheckedChange = { rememberMe = it },
                        colors = CheckboxDefaults.colors(
                            checkedColor = MaterialTheme.colorScheme.primary,
                            uncheckedColor = GrayText
                        )
                    )
                    Text("Recordarme", color = Color.White)
                }
                TextButton(onClick = { /* TODO: recuperación */ }) {
                    Text("¿Olvidaste tu contraseña?", color = Color.White)
                }
            }

            Spacer(Modifier.height(8.dp))

            Button(
                onClick = { scope.launch { doLogin() } },
                enabled = !isLoading,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (isLoading) "Ingresando…" else "Iniciar Sesión")
            }

            Spacer(Modifier.height(8.dp))
            Divider(color = GrayLine)
            Spacer(Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text("¿No tienes una cuenta? ", color = GrayText)
                TextButton(onClick = { /* TODO: nav a registro */ }) { Text("Regístrate aquí") }
            }

            Spacer(Modifier.height(16.dp))
            Divider(color = GrayLine)
            Spacer(Modifier.height(12.dp))
            Column {
                Text(
                    "Stuffies - Moda Urbana Chilena",
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Start
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    "Desde nuestro lanzamiento en junio de 2024, nos dedicamos a crear ropa moderna y de calidad con estilo estadounidense para que todos puedan vestir a la última moda.",
                    color = GrayText
                )
            }

            Spacer(Modifier.height(20.dp))

            // 👇 Apartado de usuario después del inicio de sesión
            loggedInUser?.let { username ->
                Spacer(Modifier.height(12.dp))
                UserInfoCard(username = username)
            }
        }
    }
}

@Composable
fun UserInfoCard(username: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = GrayLine
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Usuario conectado",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Nombre de usuario: $username",
                style = MaterialTheme.typography.bodyMedium,
                color = GrayText
            )
        }
    }
}

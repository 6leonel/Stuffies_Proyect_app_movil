@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example.stuffies_proyect_grupo_6.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch


private val BlackBg  = Color(0xFF000000)
private val BlackCard = Color(0xFF0D0D0D)
private val GrayText = Color(0xFFBFBFBF)
private val GrayLine = Color(0xFF1F2937)
private val CardWhite = Color(0xFFFFFFFF)

@Composable
fun ContactoScreen() {
    val snackbar = remember { SnackbarHostState() }

    // Estado del formulario
    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") } // opcional
    var comentario by remember { mutableStateOf("") }

    var sending by remember { mutableStateOf(false) }
    var okMsg by remember { mutableStateOf(false) }

    fun resetForm() {
        nombre = ""; correo = ""; comentario = ""; okMsg = false
    }

    fun validar(): String? {
        if (nombre.isBlank()) return "Ingresa tu nombre."
        if (correo.isNotBlank() && !correo.contains("@")) return "Correo inválido."
        if (comentario.isBlank()) return "Escribe tu comentario."
        if (comentario.length > 500) return "Máximo 500 caracteres."
        return null
    }

    val scope = rememberCoroutineScope()

    Scaffold(
        containerColor = BlackBg,
        contentColor = Color.White,
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = BlackBg,
                    titleContentColor = Color.White
                ),
                title = { Text("Contacto") }
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbar) }
    ) { inner ->
        Column(
            modifier = Modifier
                .padding(inner)
                .fillMaxSize()
                .background(BlackBg)
                .verticalScroll(rememberScrollState())
        ) {
            // HERO
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BlackBg)
                    .padding(horizontal = 16.dp, vertical = 24.dp)
            ) {
                Text("Contacto", style = MaterialTheme.typography.headlineMedium)
                Spacer(Modifier.height(6.dp))
                Text(
                    "¿Dudas o comentarios? Completa el formulario y te responderemos.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = GrayText
                )
            }

            // CARD blanca con el formulario
            Card(
                colors = CardDefaults.cardColors(containerColor = CardWhite),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                Column(Modifier.padding(24.dp)) {
                    // Nombre
                    OutlinedTextField(
                        value = nombre,
                        onValueChange = { if (it.length <= 100) nombre = it },
                        label = { Text("Nombre") },
                        singleLine = true,
                        colors = textFieldColorsWhite(),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(Modifier.height(12.dp))

                    // Correo (opcional)
                    OutlinedTextField(
                        value = correo,
                        onValueChange = { if (it.length <= 100) correo = it },
                        label = { Text("Correo (opcional)") },
                        singleLine = true,
                        colors = textFieldColorsWhite(),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(Modifier.height(12.dp))

                    // Comentario
                    OutlinedTextField(
                        value = comentario,
                        onValueChange = {
                            comentario = if (it.length <= 500) it else it.take(500)
                        },
                        label = { Text("Comentario") },
                        colors = textFieldColorsWhite(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 120.dp),
                        maxLines = 8,
                        visualTransformation = VisualTransformation.None
                    )

                    Spacer(Modifier.height(16.dp))

                    // Botones
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Button(
                            onClick = {
                                val err = validar()
                                if (err != null) {
                                    scope.launch { snackbar.showSnackbar(err, withDismissAction = true) }
                                    return@Button
                                }
                                sending = true
                                scope.launch {
                                    // simulación de envío
                                    kotlinx.coroutines.delay(250)
                                    sending = false
                                    okMsg = true
                                    resetForm() // como en el HTML, limpiar tras enviar
                                    snackbar.showSnackbar("¡Gracias! Te contactaremos pronto.")
                                }
                            },
                            enabled = !sending
                        ) {
                            Text(if (sending) "Enviando…" else "Enviar")
                        }

                        Spacer(Modifier.width(8.dp))

                        OutlinedButton(
                            onClick = { resetForm() }
                        ) { Text("Limpiar") }
                    }

                    if (okMsg) {
                        Spacer(Modifier.height(12.dp))
                        Text(
                            "¡Gracias! Te contactaremos pronto.",
                            color = Color(0xFF22A06B)
                        )
                    }
                }
            }

            // About preview (como en tus otras pantallas)
            Spacer(Modifier.height(16.dp))
            Divider(color = GrayLine)
            Spacer(Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(Modifier.weight(1f)) {
                    Text(
                        "Stuffies - Moda Urbana Chilena",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        "Desde nuestro lanzamiento en junio de 2024, nos dedicamos a crear ropa moderna y de calidad con estilo estadounidense para que todos puedan vestir a la última moda.",
                        color = GrayText
                    )
                }
                Spacer(Modifier.width(12.dp))
                Card(
                    colors = CardDefaults.cardColors(containerColor = BlackCard),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    AsyncImage(
                        model = "https://i.postimg.cc/R0phZ77L/ESTRELLA-BLANCA.png",
                        contentDescription = "Estrella Stuffies",
                        modifier = Modifier.size(96.dp)
                    )
                }
            }

            Spacer(Modifier.height(20.dp))
        }
    }
}

/* ---------- Colores de TextField para card blanca ---------- */
@Composable
private fun textFieldColorsWhite() = OutlinedTextFieldDefaults.colors(
    unfocusedContainerColor = CardWhite,
    focusedContainerColor = CardWhite,
    unfocusedTextColor = Color.Black,
    focusedTextColor = Color.Black,
    unfocusedLabelColor = Color(0xFF6B7280),
    focusedLabelColor = Color(0xFF111111),
    unfocusedBorderColor = Color(0xFFB0B0B0),
    focusedBorderColor = Color(0xFF111111),
    cursorColor = Color(0xFF111111)
)

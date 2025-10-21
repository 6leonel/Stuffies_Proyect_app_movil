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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.stuffies_proyect_grupo_6.navigation.Route
import com.example.stuffies_proyect_grupo_6.viewmodel.UsuarioViewModel
import kotlinx.coroutines.launch
import androidx.compose.ui.text.input.VisualTransformation


private val BlackBg   = Color(0xFF000000)
private val GrayText  = Color(0xFFBFBFBF)
private val GrayLine  = Color(0xFF1F2937)
private val CardWhite = Color(0xFFFFFFFF)

@Composable
fun RegistroScreen(
    navController: NavHostController,
    viewModel: UsuarioViewModel // lo mantenemos por tu sub-graph (no es obligatorio usarlo)
) {
    val scope = rememberCoroutineScope()
    val snackbar = remember { SnackbarHostState() }

    // ---- Estados de formulario (como en el HTML) ----
    var run by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var user by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var pass2 by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }

    // rol con dropdown
    val roles = listOf("cliente", "vendedor", "admin")
    var role by remember { mutableStateOf("") }
    var roleMenu by remember { mutableStateOf(false) }

    var creating by remember { mutableStateOf(false) }
    var okMsg by remember { mutableStateOf(false) }

    fun ok(text: String) = scope.launch { snackbar.showSnackbar(text) }
    fun err(text: String) = scope.launch {
        snackbar.showSnackbar(text, withDismissAction = true)
    }

    // ---- Validaciones equivalentes al HTML ----
    fun validar(): Boolean {
        // RUN: solo dígitos, sin puntos ni guion, largo 7–9 (para K simplificado)
        val runClean = run.trim()
        if (runClean.isEmpty() || runClean.any { !it.isDigit() } || runClean.length !in 7..9) {
            err("RUN inválido (usa solo números, sin puntos ni guion).")
            return false
        }
        if (nombre.isBlank()) { err("Ingresa tu nombre."); return false }
        if (apellidos.isBlank()) { err("Ingresa tus apellidos."); return false }
        if (email.isBlank() || !email.contains("@")) { err("Correo inválido."); return false }
        if (user.isBlank()) { err("Ingresa un usuario."); return false }
        if (pass.length !in 4..10) { err("La contraseña debe tener 4–10 caracteres."); return false }
        if (pass2 != pass) { err("Las contraseñas no coinciden."); return false }
        if (address.isBlank()) { err("Ingresa tu dirección."); return false }
        if (role.isBlank()) { err("Selecciona el tipo de usuario."); return false }
        return true
    }

    // ---- Guardar / Continuar ----
    fun onCrearCuenta() = scope.launch {
        if (!validar()) return@launch
        creating = true

        // Si tu VM tiene setters, puedes des-comentar y alinear nombres:
        // runCatching { viewModel.updateRun(run) }
        // runCatching { viewModel.updateNombre(nombre) }
        // runCatching { viewModel.updateApellidos(apellidos) }
        // runCatching { viewModel.updateEmail(email) }
        // runCatching { viewModel.updateUsuario(user) }
        // runCatching { viewModel.updatePassword(pass) }
        // runCatching { viewModel.updateDireccion(address) }
        // runCatching { viewModel.updateRol(role) }

        // Simulación de “guardar” breve
        kotlinx.coroutines.delay(250)
        creating = false
        okMsg = true
        ok("¡Cuenta verificada! Revisa el resumen.")
        // Manteniendo tu flujo: ir a Resumen (usa el mismo VM del sub-graph)
        navController.navigate(Route.Resumen.path)
    }

    fun onIrLogin() {
        navController.navigate(Route.Login.path)
    }

    // ---- UI ----
    Scaffold(
        containerColor = BlackBg,
        contentColor = Color.White,
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = BlackBg,
                    titleContentColor = Color.White
                ),
                title = { Text("Crear cuenta") }
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
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            // ----- Card blanca (como .cardx del HTML) -----
            Card(
                colors = CardDefaults.cardColors(containerColor = CardWhite),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(Modifier.padding(24.dp)) {
                    Text("Crear cuenta", color = Color.Black, style = MaterialTheme.typography.titleLarge)

                    Spacer(Modifier.height(16.dp))

                    // Fila 1: RUN / Nombre / Apellidos
                    Row(Modifier.fillMaxWidth()) {
                        OutlinedFieldWhite(
                            value = run, onValueChange = { if (it.length <= 9) run = it.filter { c -> c.isDigit() } },
                            label = "RUN (sin puntos ni guion)",
                            modifier = Modifier.weight(1f).padding(end = 8.dp)
                        )
                        OutlinedFieldWhite(
                            value = nombre, onValueChange = { if (it.length <= 50) nombre = it },
                            label = "Nombre",
                            modifier = Modifier.weight(1f).padding(horizontal = 4.dp)
                        )
                        OutlinedFieldWhite(
                            value = apellidos, onValueChange = { if (it.length <= 100) apellidos = it },
                            label = "Apellidos",
                            modifier = Modifier.weight(1f).padding(start = 8.dp)
                        )
                    }

                    Spacer(Modifier.height(12.dp))

                    // Fila 2: Correo / Usuario
                    Row(Modifier.fillMaxWidth()) {
                        OutlinedFieldWhite(
                            value = email, onValueChange = { if (it.length <= 100) email = it },
                            label = "Correo",
                            modifier = Modifier.weight(1f).padding(end = 8.dp)
                        )
                        OutlinedFieldWhite(
                            value = user, onValueChange = { if (it.length <= 30) user = it },
                            label = "Usuario",
                            modifier = Modifier.weight(1f).padding(start = 8.dp)
                        )
                    }

                    Spacer(Modifier.height(12.dp))

                    // Fila 3: Pass / Pass2
                    Row(Modifier.fillMaxWidth()) {
                        OutlinedFieldWhite(
                            value = pass, onValueChange = { if (it.length <= 10) pass = it },
                            label = "Contraseña (4–10)",
                            isPassword = true,
                            modifier = Modifier.weight(1f).padding(end = 8.dp)
                        )
                        OutlinedFieldWhite(
                            value = pass2, onValueChange = { if (it.length <= 10) pass2 = it },
                            label = "Repite la contraseña",
                            isPassword = true,
                            modifier = Modifier.weight(1f).padding(start = 8.dp)
                        )
                    }

                    Spacer(Modifier.height(12.dp))

                    // Fila 4: Dirección / Rol
                    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        OutlinedFieldWhite(
                            value = address, onValueChange = { if (it.length <= 300) address = it },
                            label = "Dirección",
                            modifier = Modifier.weight(1f).padding(end = 8.dp)
                        )

                        // Dropdown Rol (en blanco)
                        ExposedDropdownMenuBox(
                            expanded = roleMenu,
                            onExpandedChange = { roleMenu = !roleMenu },
                            modifier = Modifier.weight(1f).padding(start = 8.dp)
                        ) {
                            OutlinedTextField(
                                readOnly = true,
                                value = if (role.isBlank()) "Seleccione…" else role,
                                onValueChange = {},
                                label = { Text("Tipo de usuario") },
                                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = roleMenu) },
                                modifier = Modifier.menuAnchor().fillMaxWidth(),
                                colors = textFieldColorsWhite()
                            )
                            ExposedDropdownMenu(
                                expanded = roleMenu,
                                onDismissRequest = { roleMenu = false }
                            ) {
                                roles.forEach { r ->
                                    DropdownMenuItem(
                                        text = { Text(r) },
                                        onClick = {
                                            role = r
                                            roleMenu = false
                                        }
                                    )
                                }
                            }
                        }
                    }

                    Spacer(Modifier.height(16.dp))

                    Row {
                        Button(
                            onClick = { onCrearCuenta() },
                            enabled = !creating
                        ) { Text(if (creating) "Creando…" else "Crear cuenta") }

                        Spacer(Modifier.width(8.dp))

                        OutlinedButton(onClick = { onIrLogin() }) {
                            Text("Ya tengo cuenta")
                        }
                    }

                    if (okMsg) {
                        Spacer(Modifier.height(12.dp))
                        Text("¡Cuenta creada! Revisarás un resumen ahora…", color = Color(0xFF22A06B))
                    }
                }
            }
        }
    }
}

/* ---------- Helpers UI (Card blanca con mismos colores de borde que M3 por defecto) ---------- */

@Composable
private fun OutlinedFieldWhite(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isPassword: Boolean = false,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = true,
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        colors = textFieldColorsWhite(),
        modifier = modifier
    )
}

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

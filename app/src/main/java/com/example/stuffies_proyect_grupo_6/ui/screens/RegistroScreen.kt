package com.example.stuffies_proyect_grupo_6.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.stuffies_proyect_grupo_6.viewmodel.UsuarioViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroScreen(
    vm: UsuarioViewModel,
    onContinuar: () -> Unit
) {
    val ui by vm.uiState.collectAsState()
    val errs by vm.errores.collectAsState()

    Scaffold(topBar = { TopAppBar(title = { Text("Registro de Usuario") }) }) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = ui.nombre,
                onValueChange = vm::onNombreChange,
                label = { Text("Nombre") },
                singleLine = true,
                isError = errs.nombre != null,
                supportingText = { errs.nombre?.let { Text(it) } },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = ui.correo,
                onValueChange = vm::onCorreoChange,
                label = { Text("Correo") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                isError = errs.correo != null,
                supportingText = { errs.correo?.let { Text(it) } },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = ui.clave,
                onValueChange = vm::onClaveChange,
                label = { Text("Clave") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                isError = errs.clave != null,
                supportingText = { errs.clave?.let { Text(it) } },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = ui.direccion,
                onValueChange = vm::onDireccionChange,
                label = { Text("Dirección") },
                singleLine = true,
                isError = errs.direccion != null,
                supportingText = { errs.direccion?.let { Text(it) } },
                modifier = Modifier.fillMaxWidth()
            )
            Row(modifier = Modifier.fillMaxWidth()) {
                Checkbox(
                    checked = ui.aceptaTerminos,
                    onCheckedChange = { vm.onAceptaChange(it) }
                )
                Column {
                    Text("Acepto los términos y condiciones")
                    if (errs.aceptaTerminos != null) {
                        Text(errs.aceptaTerminos!!, color = MaterialTheme.colorScheme.error)
                    }
                }
            }
            Button(
                onClick = { if (vm.validarFormulario()) onContinuar() },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Continuar") }
        }
    }
}

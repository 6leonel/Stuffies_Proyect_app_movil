package com.example.stuffies_proyect_grupo_6.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductoDetalleScreen(
    id: Int,
    onSeguirComprando: () -> Unit = {},
    onAgregar: (Int, String, String) -> Unit = { _,_,_ -> }
) {
    val p = remember(id) { Catalogo.productoPorId(id) }

    if (p == null) {
        ScreenScaffold(title = "Detalle") {
            Text("Producto no encontrado.")
        }
        return
    }

    var talla by rememberSaveable { mutableStateOf(p.tallas.firstOrNull() ?: "Única") }
    var color by rememberSaveable { mutableStateOf(p.colores.firstOrNull() ?: "Único") }

    ScreenScaffold(title = p.nombre) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            AsyncImage(
                model = p.imagen,
                contentDescription = p.nombre,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )
            Text(text = clp(p.precio), style = MaterialTheme.typography.titleLarge)
            if (p.descripcion.isNotBlank()) Text(p.descripcion)

            // Talla
            var openTalla by remember { mutableStateOf(false) }
            ExposedDropdownMenuBox(expanded = openTalla, onExpandedChange = { openTalla = it }) {
                OutlinedTextField(
                    value = talla,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Talla") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = openTalla) },
                    modifier = Modifier.menuAnchor().fillMaxWidth()
                )
                ExposedDropdownMenu(expanded = openTalla, onDismissRequest = { openTalla = false }) {
                    (p.tallas.ifEmpty { listOf(talla) }).forEach { t ->
                        DropdownMenuItem(text = { Text(t) }, onClick = { talla = t; openTalla = false })
                    }
                }
            }

            // Color
            var openColor by remember { mutableStateOf(false) }
            ExposedDropdownMenuBox(expanded = openColor, onExpandedChange = { openColor = it }) {
                OutlinedTextField(
                    value = color,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Color") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = openColor) },
                    modifier = Modifier.menuAnchor().fillMaxWidth()
                )
                ExposedDropdownMenu(expanded = openColor, onDismissRequest = { openColor = false }) {
                    (p.colores.ifEmpty { listOf(color) }).forEach { c ->
                        DropdownMenuItem(text = { Text(c) }, onClick = { color = c; openColor = false })
                    }
                }
            }

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(onClick = { onAgregar(p.id, talla, color) }) { Text("Añadir") }
                OutlinedButton(onClick = onSeguirComprando) { Text("Seguir comprando") }
            }
        }
    }
}

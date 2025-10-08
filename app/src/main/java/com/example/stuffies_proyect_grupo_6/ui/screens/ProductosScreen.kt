package com.example.stuffies_proyect_grupo_6.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductosScreen(
    onVerDetalle: (Int) -> Unit = {},
    onAgregar: (Int) -> Unit = {}
) {
    var categoria by rememberSaveable { mutableStateOf("todos") }
    var rango by rememberSaveable { mutableStateOf("Todos") }

    val categorias = remember { Catalogo.categorias() }
    val rangos = listOf("Todos", "0-20000", "20000-35000", "35000-50000", "50000+")

    fun enRango(precio: Int, sel: String) = when (sel) {
        "0-20000" -> precio <= 20000
        "20000-35000" -> precio in 20000..35000
        "35000-50000" -> precio in 35000..50000
        "50000+" -> precio >= 50000
        else -> true
    }

    val lista = remember(categoria, rango) {
        Catalogo.productos.filter { p ->
            val okCat = categoria == "todos" || p.categoria.trim() == categoria
            val okPrecio = enRango(p.precio, rango)
            okCat && okPrecio
        }
    }

    ScreenScaffold(
        title = "Productos",
        backgroundColor = Color.Black               // 👈 fondo negro solo aquí
    ) {
        // Filtros
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Categoría
            var openCat by remember { mutableStateOf(false) }
            ExposedDropdownMenuBox(expanded = openCat, onExpandedChange = { openCat = it }) {
                OutlinedTextField(
                    value = categoria,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Categoría") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = openCat) },
                    modifier = Modifier.menuAnchor().weight(1f)
                )
                ExposedDropdownMenu(expanded = openCat, onDismissRequest = { openCat = false }) {
                    categorias.forEach { c ->
                        DropdownMenuItem(text = { Text(c) }, onClick = { categoria = c; openCat = false })
                    }
                }
            }

            // Precio
            var openPrecio by remember { mutableStateOf(false) }
            ExposedDropdownMenuBox(expanded = openPrecio, onExpandedChange = { openPrecio = it }) {
                OutlinedTextField(
                    value = rango,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Precio") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = openPrecio) },
                    modifier = Modifier.menuAnchor().weight(1f)
                )
                ExposedDropdownMenu(expanded = openPrecio, onDismissRequest = { openPrecio = false }) {
                    rangos.forEach { r ->
                        DropdownMenuItem(text = { Text(r) }, onClick = { rango = r; openPrecio = false })
                    }
                }
            }
        }

        Spacer(Modifier.height(12.dp))

        // Grilla
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 160.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(lista, key = { it.id }) { p ->
                ProductoCardDark(
                    p = p,
                    onVer = { onVerDetalle(p.id) },
                    onAgregar = { onAgregar(p.id) }
                )
            }
        }
    }
}

@Composable
private fun ProductoCardDark(
    p: Producto,
    onVer: () -> Unit,
    onAgregar: () -> Unit
) {
    var mostrarAlt by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E2339)) // 👈 card oscura
    ) {
        Column(Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            AsyncImage(
                model = if (mostrarAlt && !p.imagenHover.isNullOrBlank()) p.imagenHover else p.imagen,
                contentDescription = p.nombre,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .clickable { mostrarAlt = !mostrarAlt },
                contentScale = ContentScale.Crop
            )
            Text(p.nombre, fontWeight = FontWeight.SemiBold, maxLines = 2, color = Color.White)
            Text(clp(p.precio), color = Color(0xFFB9B9D6))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = onVer) { Text("Ver detalles") }
                Button(onClick = onAgregar) { Text("Añadir") }
            }
        }
    }
}

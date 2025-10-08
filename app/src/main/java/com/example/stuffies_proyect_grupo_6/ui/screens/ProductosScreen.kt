package com.example.stuffies_proyect_grupo_6.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

data class ProductoUi(
    val id: Int,
    val nombre: String,
    val precio: String,
    val imagen: String
)

@Composable
fun ProductosScreen(
    onVerDetalle: (Int) -> Unit = {},
    onAgregar: (Int) -> Unit = {}
) {
    val productos = listOf(
        ProductoUi(1, "Polera Oversize Blanca", "$14.990", "https://i.postimg.cc/8z3V4dmt/polera-oversize-blanca.jpg"),
        ProductoUi(2, "Polerón Hoodie Negro", "$24.990", "https://i.postimg.cc/6qxVgQ7B/hoodie-negro.jpg"),
        ProductoUi(3, "Jeans Tapered Azul", "$29.990", "https://i.postimg.cc/Vv4vZB1b/jeans-azul.jpg"),
        ProductoUi(4, "Gorro Beanie Gris", "$9.990", "https://i.postimg.cc/fL2wfx3B/beanie-gris.jpg"),
        ProductoUi(5, "Chaqueta Bomber Verde", "$39.990", "https://i.postimg.cc/6QwYG6qj/bomber-verde.jpg"),
        ProductoUi(6, "Zapatillas Urbanas", "$49.990", "https://i.postimg.cc/2y6wF9B0/zapatillas-urbanas.jpg")
    )

    ScreenScaffold(title = "Productos") {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 160.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(productos) { p ->
                ProductoCard(p, onVer = { onVerDetalle(p.id) }, onAgregar = { onAgregar(p.id) })
            }
        }
    }
}

@Composable
private fun ProductoCard(
    p: ProductoUi,
    onVer: () -> Unit,
    onAgregar: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AsyncImage(
                model = p.imagen,
                contentDescription = p.nombre,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
            Text(p.nombre, fontWeight = FontWeight.SemiBold)
            Text(p.precio)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = onVer) { Text("Ver") }
                Button(onClick = onAgregar) { Text("Añadir") }
            }
        }
    }
}

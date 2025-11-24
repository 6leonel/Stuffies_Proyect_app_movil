@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example.stuffies_proyect_grupo_6.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.stuffies_proyect_grupo_6.viewmodel.PostViewModel
import java.text.NumberFormat
import java.util.Locale

@Composable
fun PostsScreen(
    navController: NavController
) {
    val vm: PostViewModel = viewModel()
    val posts by vm.posts.collectAsState()
    val loading by vm.loading.collectAsState()
    val error by vm.error.collectAsState()   // lo seguimos usando sólo para la pantalla de error

    LaunchedEffect(Unit) {
        vm.cargarPosts()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Productos (microservicio)") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            when {
                loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                posts.isNotEmpty() -> {
                    // ✅ Hay productos (sea de la API o del catálogo local)
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(posts) { p ->
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                                shape = RoundedCornerShape(16.dp)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(12.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    // Imagen del producto
                                    AsyncImage(
                                        model = p.imagen,
                                        contentDescription = p.nombre,
                                        modifier = Modifier
                                            .size(80.dp)
                                            .clip(RoundedCornerShape(12.dp)),
                                        contentScale = ContentScale.Crop
                                    )

                                    Spacer(modifier = Modifier.width(12.dp))

                                    Column(
                                        modifier = Modifier.weight(1f)
                                    ) {
                                        Text(
                                            text = p.nombre,
                                            style = MaterialTheme.typography.titleMedium
                                        )
                                        Text(
                                            text = "Categoría: ${p.categoria}",
                                            style = MaterialTheme.typography.labelSmall
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = "Precio: ${formatCLP(p.precio)}",
                                            style = MaterialTheme.typography.bodyMedium
                                        )
                                        if (p.descripcion.isNotBlank()) {
                                            Spacer(modifier = Modifier.height(4.dp))
                                            Text(
                                                text = p.descripcion,
                                                style = MaterialTheme.typography.bodySmall,
                                                maxLines = 2
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                // Sólo se muestra si NO hay productos y además hubo error
                error != null -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No se pudieron cargar los productos desde el microservicio.\n" +
                                    "Revisa que el backend Spring Boot esté levantado en el puerto 8080.",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }

                else -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No hay productos disponibles en este momento.",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}

/**
 * Formatea el precio como CLP, ejemplo: 39990 -> $39.990
 */
private fun formatCLP(precio: Number): String {
    val nf = NumberFormat.getInstance(Locale("es", "CL"))
    return "$" + nf.format(precio.toLong())
}

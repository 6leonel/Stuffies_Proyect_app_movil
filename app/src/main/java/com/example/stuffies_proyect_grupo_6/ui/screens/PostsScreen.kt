@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example.stuffies_proyect_grupo_6.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.stuffies_proyect_grupo_6.viewmodel.PostViewModel

@Composable
fun PostsScreen(
    navController: NavController
) {
    val vm: PostViewModel = viewModel()
    val posts by vm.posts.collectAsState()
    val loading by vm.loading.collectAsState()

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
                        contentAlignment = androidx.compose.ui.Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                posts.isEmpty() -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = androidx.compose.ui.Alignment.Center
                    ) {
                        Text("Sin datos. ¿Está levantado el microservicio?")
                    }
                }

                else -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(posts) { p ->
                            Card(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.padding(12.dp)) {
                                    Text(
                                        text = p.nombre,
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                    Text(
                                        text = "Categoría: ${p.categoria}",
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                    Text(
                                        text = "Precio: ${p.precio}",
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                    if (!p.descripcion.isNullOrBlank()) {
                                        Text(
                                            text = p.descripcion!!,
                                            style = MaterialTheme.typography.bodySmall
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@file:OptIn(
    androidx.compose.material3.ExperimentalMaterial3Api::class,
    androidx.compose.foundation.ExperimentalFoundationApi::class
)

package com.example.stuffies_proyect_grupo_6.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

// ===== Colores fijos para “negro total” =====
private val BlackBg = Color(0xFF000000)
private val BlackCard = Color(0xFF0D0D0D)
private val GrayText = Color(0xFFBFBFBF)
private val GrayOutline = Color(0xFF6B7280)

// ===== Data mock =====
private data class BlogPost(
    val id: String,
    val titulo: String,
    val fecha: String,
    val categoria: String,
    val extracto: String,
    val imagen: String
)

private val BLOGS = listOf(
    BlogPost(
        "b001",
        "Nuestra Historia",
        "2025-09-30",
        "Noticias",
        "Cómo partimos: del boceto a la primera colección.",
        "https://i.postimg.cc/PxD35Mgg/DADOS.png"
    ),
    BlogPost(
        "b002",
        "Historias que visten distinto",
        "2025-09-21",
        "Estilo",
        "Moda, creatividad y experiencias que trascienden la ropa.",
        "https://i.postimg.cc/Dz8pthBy/448330999-1571729430349986-3007445543010160619-n.jpg"
    ),
    BlogPost(
        "b003",
        "Estilo y cultura urbana",
        "2025-09-15",
        "Estilo",
        "Tendencias y consejos para inspirar tu look diario.",
        "https://i.postimg.cc/DwCYj0QM/IMG-1162-2.jpg"
    ),
    BlogPost(
        "b004",
        "Guía de tallas Stuffies",
        "2025-09-10",
        "Tutoriales",
        "Aprende a medirte en casa y elegir tu talla ideal.",
        "https://stuffiesconcept.com/cdn/shop/files/WhiteDice1.png?v=1753404231&width=600"
    )
)

// Mock para el carrusel de arriba
private val HIGHLIGHTS = listOf(
    BlogPost(
        "h001",
        "Drop limitado DICE",
        "2025-10-05",
        "Colección",
        "Sneak peek de la colección otoño-invierno.",
        "https://i.postimg.cc/DwCYj0QM/IMG-1162-2.jpg"
    )
)

// ===== Pantalla =====
@Composable
fun BlogsScreen() {
    Scaffold(
        containerColor = BlackBg,
        contentColor = Color.White,
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = BlackBg,
                    titleContentColor = Color.White
                ),
                title = { Text("Blogs") }
            )
        }
    ) { inner ->
        Column(
            modifier = Modifier
                .padding(inner)
                .fillMaxSize()
                .background(BlackBg)
                .navigationBarsPadding()
        ) {
            HeroSection()
            CarouselSection()
            AdviceSection()
            BlogsListSection()
            AboutPreviewSection()
        }
    }
}

// ===== Hero =====
@Composable
private fun HeroSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(BlackBg)
            .padding(vertical = 24.dp, horizontal = 16.dp)
    ) {
        Column {
            Text(
                "BLOGS",
                color = Color.White,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(6.dp))
            Text(
                "“Más que ropa: historias, estilos y novedades que te inspiran a vestir distinto.”",
                color = GrayText,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

// ===== Carrusel destacado =====
@Composable
private fun CarouselSection() {
    val pagerState = rememberPagerState(pageCount = { HIGHLIGHTS.size })

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(BlackBg)
            .padding(bottom = 16.dp)
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
        ) { page ->
            val item = HIGHLIGHTS[page]
            ElevatedCard(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                colors = CardDefaults.elevatedCardColors(containerColor = BlackCard)
            ) {
                Column {
                    AsyncImage(
                        model = item.imagen,
                        contentDescription = item.titulo,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(140.dp),
                        contentScale = ContentScale.Crop
                    )
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = item.titulo,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = item.extracto,
                            style = MaterialTheme.typography.bodyMedium,
                            color = GrayText,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(HIGHLIGHTS.size) { index ->
                val selected = pagerState.currentPage == index
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .size(if (selected) 10.dp else 6.dp)
                        .background(
                            color = if (selected) Color.White else GrayOutline,
                            shape = MaterialTheme.shapes.small
                        )
                )
            }
        }
    }
}

// ===== Sección de consejos =====
@Composable
private fun AdviceSection() {
    // Lista de consejos predefinidos en español
    val consejos = listOf(
        "Viste para ti, no para los demás.",
        "La confianza es el mejor atuendo. Lúcelo.",
        "No tengas miedo de experimentar con colores y patrones.",
        "Un buen par de zapatillas puede cambiar tu día.",
        "La moda se desvanece, el estilo es eterno.",
        "Menos es más. A veces, la simplicidad es la clave.",
        "Los accesorios adecuados pueden transformar cualquier look."
    )

    // Selecciona un consejo al azar para mostrar
    val consejoDelDia = remember { consejos.random() }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Tip del día",
            style = MaterialTheme.typography.titleMedium,
            color = Color.White
        )
        Spacer(Modifier.height(8.dp))
        ElevatedCard(
            colors = CardDefaults.elevatedCardColors(containerColor = BlackCard)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "\"$consejoDelDia\"",
                    style = MaterialTheme.typography.bodyMedium,
                    color = GrayText
                )
            }
        }
        Spacer(Modifier.height(24.dp))
    }
}


// ===== Lista + filtros =====
@Composable
private fun BlogsListSection() {
    var query by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("Todos") }
    var isCategoriaExpanded by remember { mutableStateOf(false) }

    val categorias = listOf("Todos") + BLOGS.map { it.categoria }.distinct()

    val filteredBlogs = remember(query, categoria) {
        BLOGS.filter { blog ->
            val matchesQuery = blog.titulo.contains(query, ignoreCase = true)
            val matchesCategoria = categoria == "Todos" || blog.categoria == categoria
            matchesQuery && matchesCategoria
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(BlackBg)
            .padding(horizontal = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                label = { Text("Buscar", color = GrayText) },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = BlackCard,
                    unfocusedContainerColor = BlackCard,
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = GrayOutline,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedLabelColor = GrayText,
                    unfocusedLabelColor = GrayText,
                )
            )

            Spacer(Modifier.width(8.dp))

            ExposedDropdownMenuBox(
                expanded = isCategoriaExpanded,
                onExpandedChange = { isCategoriaExpanded = !isCategoriaExpanded }
            ) {
                OutlinedTextField(
                    value = categoria,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Categoría", color = GrayText) },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isCategoriaExpanded) },
                    modifier = Modifier.menuAnchor(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = BlackCard,
                        unfocusedContainerColor = BlackCard,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = GrayOutline,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedLabelColor = GrayText,
                        unfocusedLabelColor = GrayText,
                    )
                )
                ExposedDropdownMenu(
                    expanded = isCategoriaExpanded,
                    onDismissRequest = { isCategoriaExpanded = false }
                ) {
                    categorias.forEach { cat ->
                        DropdownMenuItem(
                            text = { Text(cat) },
                            onClick = {
                                categoria = cat
                                isCategoriaExpanded = false
                            }
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Adaptive(160.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .background(BlackBg)
        ) {
            items(filteredBlogs) { blog ->
                ElevatedCard(
                    colors = CardDefaults.elevatedCardColors(containerColor = BlackCard)
                ) {
                    Column {
                        AsyncImage(
                            model = blog.imagen,
                            contentDescription = blog.titulo,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp),
                            contentScale = ContentScale.Crop
                        )
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = blog.titulo,
                                style = MaterialTheme.typography.titleSmall,
                                color = Color.White,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Spacer(Modifier.height(2.dp))
                            Text(
                                text = blog.extracto,
                                style = MaterialTheme.typography.bodySmall,
                                color = GrayText,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }
        }
    }
}

// ===== Sección “Sobre el blog” =====
@Composable
private fun AboutPreviewSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(BlackBg)
            .padding(16.dp)
    ) {
        Text(
            text = "¿Qué encontrarás en el blog de Stuffies?",
            style = MaterialTheme.typography.titleMedium,
            color = Color.White
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = "Historias, lanzamientos, inspiración de looks y contenido detrás de cámaras de la marca. " +
                    "Queremos que vistas distinto, pero también que entiendas las ideas y procesos detrás de cada colección.",
            style = MaterialTheme.typography.bodyMedium,
            color = GrayText
        )
        Spacer(Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Nuevas entradas cada mes.",
                style = MaterialTheme.typography.bodyMedium,
                color = GrayText
            )
            ElevatedCard(
                colors = CardDefaults.elevatedCardColors(containerColor = BlackCard)
            ) {
                AsyncImage(
                    model = "https://i.postimg.cc/R0phZ77L/ESTRELLA-BLANCA.png",
                    contentDescription = "Estrella Stuffies",
                    modifier = Modifier.size(96.dp)
                )
            }
        }
        Spacer(Modifier.height(12.dp))
    }
}

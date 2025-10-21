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
    BlogPost("b001","Nuestra Historia","2025-09-30","Noticias",
        "Cómo partimos: del boceto a la primera colección.",
        "https://i.postimg.cc/PxD35Mgg/DADOS.png"),
    BlogPost("b002","Historias que visten distinto","2025-09-21","Estilo",
        "Moda, creatividad y experiencias que trascienden la ropa.",
        "https://i.postimg.cc/Dz8pthBy/448330999-1571729430349986-3007445543010160619-n.jpg"),
    BlogPost("b003","Estilo y cultura urbana","2025-09-15","Estilo",
        "Tendencias y consejos para inspirar tu look diario.",
        "https://i.postimg.cc/DwCYj0QM/IMG-1162-2.jpg"),
    BlogPost("b004","Guía de tallas Stuffies","2025-09-10","Tutoriales",
        "Aprende a medirte en casa y elegir tu talla ideal.",
        "https://stuffiesconcept.com/cdn/shop/files/WhiteDice1.png?v=1753404231&width=1200"),
    BlogPost("b005","Cuidados de tus prendas","2025-08-28","Tutoriales",
        "Lava en frío, seca a la sombra y conserva colores vivos.",
        "https://i.postimg.cc/PxD35Mgg/DADOS.png"),
    BlogPost("b006","Nueva cápsula FW","2025-08-10","Noticias",
        "Sneak peek de la colección otoño-invierno.",
        "https://i.postimg.cc/DwCYj0QM/IMG-1162-2.jpg")
)

// ===== Pantalla =====
@Composable
fun BlogsScreen() {
    Scaffold(
        containerColor = BlackBg,        // ⬅️ fondo del Scaffold
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
        // Capa raíz NEGRA que envuelve TODO
        Column(
            modifier = Modifier
                .padding(inner)
                .fillMaxSize()
                .background(BlackBg)
                .navigationBarsPadding()
        ) {
            HeroSection()
            CarouselSection()
            BlogsListSection()
            AboutPreviewSection()
        }
    }
}

// ===== Hero (NEGRO explícito) =====
@Composable
private fun HeroSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(BlackBg)         // ⬅️ nada de surfaceVariant
            .padding(vertical = 24.dp, horizontal = 16.dp)
    ) {
        Column {
            Text("BLOGS",
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
            Spacer(Modifier.height(12.dp))
            Button(onClick = { /* ir a productos */ }) { Text("Ver productos") }
        }
    }
}

// ===== Carrusel =====
@Composable
private fun CarouselSection() {
    val images = listOf(
        "https://i.postimg.cc/PxD35Mgg/DADOS.png" to ("Blogs" to "Descubre las historias que tenemos para contar."),
        "https://i.postimg.cc/Dz8pthBy/448330999-1571729430349986-3007445543010160619-n.jpg" to ("Historias que Visten Distinto" to "Moda, creatividad y experiencias que trascienden la ropa."),
        "https://i.postimg.cc/DwCYj0QM/IMG-1162-2.jpg" to ("Estilo y Cultura Urbana" to "Tendencias, consejos y novedades para inspirar tu look diario.")
    )
    val pagerState = rememberPagerState { images.size }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(BlackBg)
            .padding(vertical = 12.dp)
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(BlackBg)
        ) { page ->
            val (img, titlePair) = images[page]
            val (title, subtitle) = titlePair
            Box(Modifier.fillMaxSize()) {
                AsyncImage(
                    model = img,
                    contentDescription = title,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .background(Color.Black.copy(alpha = 0.45f))
                        .fillMaxWidth()
                        .padding(12.dp)
                ) {
                    Text(title, color = Color.White, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                    Text(subtitle, color = Color.White, style = MaterialTheme.typography.bodySmall, maxLines = 2, overflow = TextOverflow.Ellipsis)
                }
            }
        }

        // Indicadores
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(BlackBg)
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(images.size) { ix ->
                val selected = pagerState.currentPage == ix
                Box(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .size(if (selected) 10.dp else 8.dp)
                        .background(
                            if (selected) Color.White else GrayOutline,
                            shape = MaterialTheme.shapes.small
                        )
                )
            }
        }
    }
}

// ===== Lista + Filtros + Paginación (NEGRO en inputs, menús y cards) =====
@Composable
private fun BlogsListSection() {
    var query by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("*") }
    val pageSize = 6
    var page by remember { mutableStateOf(1) }
    val categorias = listOf("*", "Noticias", "Estilo", "Tutoriales")

    val filtered = remember(query, categoria) {
        BLOGS.filter { b ->
            val q = query.trim().lowercase()
            val matchText = q.isBlank() || b.titulo.lowercase().contains(q) || b.extracto.lowercase().contains(q)
            val matchCat = categoria == "*" || b.categoria == categoria
            matchText && matchCat
        }
    }

    val totalPages = (filtered.size + pageSize - 1) / pageSize
    if (page > totalPages && totalPages > 0) page = totalPages
    if (totalPages == 0) page = 1

    val from = (page - 1) * pageSize
    val to = (from + pageSize).coerceAtMost(filtered.size)
    val pageItems = if (from in 0..filtered.size && to in 0..filtered.size) filtered.subList(from, to) else emptyList()

    Column(
        modifier = Modifier
            .background(BlackBg)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        // Filtros
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom
        ) {
            OutlinedTextField(
                value = query,
                onValueChange = { query = it; page = 1 },
                label = { Text("Buscar en blogs…") },
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = BlackBg,
                    focusedContainerColor = BlackBg,
                    unfocusedTextColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedLabelColor = GrayOutline,
                    focusedLabelColor = Color.White,
                    unfocusedBorderColor = GrayOutline,
                    focusedBorderColor = Color.White,
                    cursorColor = Color.White
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )

            var menuOpen by remember { mutableStateOf(false) }
            ExposedDropdownMenuBox(
                expanded = menuOpen,
                onExpandedChange = { menuOpen = !menuOpen },
                modifier = Modifier.widthIn(min = 160.dp)
            ) {
                OutlinedTextField(
                    readOnly = true,
                    value = categoria,
                    onValueChange = {},
                    label = { Text("Categoría") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = menuOpen) },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = BlackBg,
                        focusedContainerColor = BlackBg,
                        unfocusedTextColor = Color.White,
                        focusedTextColor = Color.White,
                        unfocusedLabelColor = GrayOutline,
                        focusedLabelColor = Color.White,
                        unfocusedBorderColor = GrayOutline,
                        focusedBorderColor = Color.White,
                        cursorColor = Color.White
                    ),
                    modifier = Modifier.menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = menuOpen,
                    onDismissRequest = { menuOpen = false },
                    containerColor = BlackCard
                ) {
                    categorias.forEach { opt ->
                        DropdownMenuItem(
                            text = { Text(opt, color = Color.White) },
                            onClick = {
                                categoria = opt
                                page = 1
                                menuOpen = false
                            }
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(12.dp))

        // Grid
        LazyVerticalGrid(
            columns = GridCells.Adaptive(260.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .background(BlackBg)
        ) {
            items(pageItems, key = { it.id }) { post ->
                ElevatedCard(
                    colors = CardDefaults.elevatedCardColors(containerColor = BlackCard),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column {
                        AsyncImage(
                            model = post.imagen,
                            contentDescription = post.titulo,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(140.dp)
                        )
                        Column(Modifier.padding(12.dp)) {
                            Text(post.titulo, color = Color.White, style = MaterialTheme.typography.titleMedium, maxLines = 2, overflow = TextOverflow.Ellipsis)
                            Spacer(Modifier.height(4.dp))
                            Text("${post.fecha} • ${post.categoria}",
                                style = MaterialTheme.typography.labelMedium,
                                color = GrayText
                            )
                            Spacer(Modifier.height(8.dp))
                            Text(post.extracto, color = Color.White, style = MaterialTheme.typography.bodyMedium, maxLines = 3, overflow = TextOverflow.Ellipsis)
                            Spacer(Modifier.height(8.dp))
                            Text("Leer más (pronto)", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
                        }
                    }
                }
            }
        }

        // Paginación
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .background(BlackBg),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedButton(
                onClick = { if (page > 1) page-- },
                enabled = page > 1,
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)
            ) { Text("Anterior") }

            Text(
                "   $page / ${maxOf(totalPages, 1)}   ",
                style = MaterialTheme.typography.bodyMedium,
                color = GrayText
            )

            OutlinedButton(
                onClick = { if (page < totalPages) page++ },
                enabled = page < totalPages,
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)
            ) { Text("Siguiente") }
        }
    }
}

// ===== About preview (NEGRO) =====
@Composable
private fun AboutPreviewSection() {
    Divider(Modifier
        .padding(vertical = 16.dp)
        .background(BlackBg), color = Color(0xFF1F2937))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(BlackBg)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(Modifier.weight(1f)) {
            Text("Stuffies - Moda Urbana Chilena", color = Color.White, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.height(6.dp))
            Text(
                "Desde nuestro lanzamiento en junio de 2024, nos dedicamos a crear ropa moderna y de calidad con estilo estadounidense para que todos puedan vestir a la última moda.",
                color = GrayText,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Spacer(Modifier.width(12.dp))
        ElevatedCard(colors = CardDefaults.elevatedCardColors(containerColor = BlackCard)) {
            AsyncImage(
                model = "https://i.postimg.cc/R0phZ77L/ESTRELLA-BLANCA.png",
                contentDescription = "Estrella Stuffies",
                modifier = Modifier.size(96.dp)
            )
        }
    }
    Spacer(Modifier.height(12.dp))
}

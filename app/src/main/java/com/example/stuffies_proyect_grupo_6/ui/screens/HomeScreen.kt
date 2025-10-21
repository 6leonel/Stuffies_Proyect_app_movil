package com.example.stuffies_proyect_grupo_6.ui.screens

import android.os.Build
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import android.content.Context
import androidx.compose.runtime.LaunchedEffect

@Composable
fun HomeScreen(
    onIrProductos: () -> Unit,
    onIrBlogs: () -> Unit,
    onIrNosotros: () -> Unit,
    onIrAnimacion: () -> Unit = {},
    onIrContacto: () -> Unit,
    onIrCarrito: () -> Unit,
    onIrLogin: () -> Unit,
    onIrPerfil: () -> Unit,
    onIrRegistro: () -> Unit
) {
    // Fondo negro
    val fondo = Brush.verticalGradient(
        colorStops = arrayOf(0f to Color.Black, 1f to Color.Black)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(fondo)
            .padding(bottom = 8.dp)
    ) {
        HomeHeader(
            onIrProductos = onIrProductos,
            onIrBlogs = onIrBlogs,
            onIrNosotros = onIrNosotros,
            onIrAnimacion = onIrAnimacion,
            onIrContacto = onIrContacto,
            onIrCarrito = onIrCarrito,
            onIrLogin = onIrLogin,
            onIrPerfil = onIrPerfil,
            onIrRegistro = onIrRegistro
        )

        Spacer(Modifier.height(8.dp))

        // Hero con GIF animado en bucle
        HeroSection(onIrProductos = onIrProductos)

        Spacer(Modifier.height(16.dp))
        CarouselSection()

        Spacer(Modifier.height(16.dp))
        FeaturedProducts(onVerProducto = { /* TODO navegar a detalle */ })

        Spacer(Modifier.height(16.dp))
        AboutPreview(onIrContacto = onIrContacto)

        Spacer(Modifier.height(12.dp))
        Footer()
    }
}

@Composable
private fun HomeHeader(
    onIrProductos: () -> Unit,
    onIrBlogs: () -> Unit,
    onIrNosotros: () -> Unit,
    onIrAnimacion: () -> Unit = {},
    onIrContacto: () -> Unit,
    onIrCarrito: () -> Unit,
    onIrLogin: () -> Unit,
    onIrPerfil: () -> Unit,
    onIrRegistro: () -> Unit = {},
) {
    val context = LocalContext.current
    val imageLoader = remember {
        ImageLoader.Builder(context)
            .components {
                if (Build.VERSION.SDK_INT >= 28) add(ImageDecoderDecoder.Factory())
                else add(GifDecoder.Factory())
            }.build()
    }

    // === Contador del carrito ===
    val ctx = LocalContext.current
    var cartCount by remember { mutableStateOf(0) }
    LaunchedEffect(Unit) { cartCount = readCartUnits(ctx) }

    Surface(color = Color.Transparent) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            // Logo + nombre
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = "https://i.postimg.cc/Hs1Q5cQB/output-onlinegiftools-1.gif",
                    imageLoader = imageLoader,
                    contentDescription = "Logo animado Stuffies",
                    modifier = Modifier.size(34.dp).clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(Modifier.width(10.dp))
                Text("STUFFIES", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            }

            // Nav + cuenta (scroll horizontal para que no se corte "Nosotros")
            Row(verticalAlignment = Alignment.CenterVertically) {

                Row(
                    modifier = Modifier.horizontalScroll(rememberScrollState()),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(onClick = onIrProductos) { Text("Productos", color = Color(0xFFB9B9D6)) }
                    TextButton(onClick = onIrBlogs)     { Text("Blogs",     color = Color(0xFFB9B9D6)) }
                    TextButton(onClick = onIrNosotros)  { Text("Nosotros",  color = Color(0xFFB9B9D6)) }
                    TextButton(onClick = onIrAnimacion) { Text("Animación", color = Color(0xFFB9B9D6)) }
                    TextButton(onClick = onIrContacto)  { Text("Contacto",  color = Color(0xFFB9B9D6)) }
                    TextButton(onClick = onIrLogin)     { Text("Inicio sesión", color = Color(0xFFB9B9D6)) }
                    TextButton(onClick = onIrRegistro)  { Text("Registro",  color = Color(0xFFB9B9D6)) }

                    // === Botón Carrito (n) ===
                    TextButton(onClick = onIrCarrito) {
                        Text(
                            text = if (cartCount > 0) "Carrito ($cartCount)" else "Carrito",
                            color = Color(0xFFB9B9D6)
                        )
                    }
                }

                Spacer(Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .size(34.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF7C5CFF))
                        .clickable { onIrPerfil() }
                )
            }
        }
    }
}

@Composable
private fun HeroSection(onIrProductos: () -> Unit) {
    val context = LocalContext.current

    Surface(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .heightIn(min = 190.dp)
            .clip(RoundedCornerShape(22.dp)),
        color = Color.Transparent,
        tonalElevation = 0.dp
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            // 🔹 Imagen de fondo estática
            AsyncImage(
                model = "https://i.postimg.cc/Dz8pthBy/448330999-1571729430349986-3007445543010160619-n.jpg",
                contentDescription = "Imagen hero Stuffies",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(210.dp)
            )

            // 🔹 Capa semitransparente para que el texto sea legible
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(Color(0x66000000))
            )

            // 🔹 Contenido textual encima de la imagen
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp)
            ) {
                Text(
                    "ROPA URBANA CON ESTILO",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    "Descubre nuestra colección de poleras, polerones, jeans y accesorios con estilo estadounidense",
                    color = Color(0xFFEAF6FF),
                    fontSize = 13.sp,
                    lineHeight = 18.sp
                )
                Spacer(Modifier.height(16.dp))
                Button(
                    onClick = onIrProductos,
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6C55F6),
                        contentColor = Color.White
                    )
                ) {
                    Text("Ver productos", fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun CarouselSection() {
    val imgs = listOf(
        "https://i.postimg.cc/J45FXPsm/santia-Asco.png",
        "https://i.postimg.cc/GpVgRf5P/IMG-1164.jpg",
        "https://i.postimg.cc/ht451Tmm/476928810-17887865937208902-5206449320773511412-n.jpg"
    )
    val pagerState = rememberPagerState(pageCount = { imgs.size })
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            val next = (pagerState.currentPage + 1) % imgs.size
            scope.launch { pagerState.animateScrollToPage(next) }
        }
    }

    Column(Modifier.fillMaxWidth()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(190.dp)
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(16.dp))
        ) { page ->
            AsyncImage(
                model = imgs[page],
                contentDescription = "Promo $page",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Spacer(Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(imgs.size) { i ->
                val active = i == pagerState.currentPage
                Box(
                    Modifier
                        .padding(horizontal = 3.dp)
                        .size(if (active) 9.dp else 7.dp)
                        .clip(CircleShape)
                        .background(if (active) Color(0xFF6C55F6) else Color(0xFF444A66))
                )
            }
        }
    }
}

@Composable
private fun FeaturedProducts(onVerProducto: (Int) -> Unit) {
    val destacados = remember { Catalogo.productos.filter { it.destacado } }

    Column(Modifier.fillMaxWidth()) {
        Text(
            "Productos Destacados",
            color = Color(0xFFEAEAF4),
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(Modifier.height(8.dp))

        if (destacados.isEmpty()) {
            Text(
                "No hay productos destacados por ahora.",
                color = Color(0xFFB9B9D6),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            return
        }

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(destacados, key = { it.id }) { p ->
                FeaturedProductCard(
                    p = p,
                    onClick = { onVerProducto(p.id) }
                )
            }
        }
    }
}

@Composable
private fun ProductCard(
    id: Int,
    onVerProducto: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .height(160.dp)
            .clip(RoundedCornerShape(14.dp))
            .clickable { onVerProducto(id) },
        color = Color(0xFF1E2339)
    ) {
        Column(Modifier.padding(12.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(92.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFF2A3152))
            )
            Spacer(Modifier.height(8.dp))
            Text(
                "Producto $id",
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                "$${id}9.990",
                color = Color(0xFFB9B9D6),
                fontSize = 12.sp
            )
        }
    }
}

@Composable
private fun AboutPreview(onIrContacto: () -> Unit) {
    Surface(
        color = Color(0xFFE9E6F3),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .heightIn(min = 120.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(Modifier.weight(1f)) {
                Text(
                    "Stuffies - Moda Urbana Chilena",
                    color = Color(0xFF24243B),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    "Desde nuestro lanzamiento en junio de 2024, nos dedicamos a crear ropa moderna y de calidad con estilo estadounidense para que todos puedan vestir a la última moda.",
                    color = Color(0xFF3E3E55),
                    fontSize = 13.sp,
                    lineHeight = 18.sp
                )
                Spacer(Modifier.height(10.dp))
                OutlinedButton(
                    onClick = onIrContacto,
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF3B3B52))
                ) { Text("Contacto") }
            }
            Spacer(Modifier.width(12.dp))
            AsyncImage(
                model = "https://i.postimg.cc/R0phZ77L/ESTRELLA-BLANCA.png",
                contentDescription = "Logo",
                modifier = Modifier
                    .size(74.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
private fun Footer() {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        text = "© 2024 Stuffies. Todos los derechos reservados.",
        color = Color(0xFFB9B9D6),
        fontSize = 12.sp
    )
}

@Composable
private fun FeaturedProductCard(
    p: Producto,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .width(220.dp)
            .height(160.dp)
            .clip(RoundedCornerShape(14.dp))
            .clickable { onClick() },
        color = Color(0xFF1E2339)
    ) {
        Column(Modifier.padding(12.dp)) {
            AsyncImage(
                model = p.imagen,
                contentDescription = p.nombre,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(92.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.height(8.dp))
            Text(
                p.nombre,
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                clp(p.precio),
                color = Color(0xFFB9B9D6),
                fontSize = 12.sp
            )
        }
    }
}

/* ===================== Helper para contador del carrito ===================== */
// Lee el total de unidades guardadas en el carrito (mismo storage que CarritoScreen)
private fun readCartUnits(ctx: Context): Int {
    val prefs = ctx.getSharedPreferences("stuffies_cart", Context.MODE_PRIVATE)
    val raw = prefs.getString("carrito_raw", "") ?: ""
    if (raw.isBlank()) return 0
    val SEP = "\u001F"
    return raw.lineSequence()
        .filter { it.isNotBlank() }
        .map { it.split(SEP).getOrNull(3)?.toIntOrNull() ?: 1 } // campo "cantidad"
        .sum()
}

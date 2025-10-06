package com.example.stuffies_proyect_grupo_6.ui.screens

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.example.stuffies_proyect_grupo_6.R

@Composable
fun HomeMedium(
    onVerProductos: () -> Unit = {},
    onIrContacto: () -> Unit = {}
) {
    val bg = Brush.verticalGradient(listOf(Color(0xFF111827), Color(0xFF1F2937)))

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(bg)
            .systemBarsPadding()
            .padding(20.dp)
    ) {
        // IZQUIERDA: Header + Hero
        Column(Modifier.weight(1f)) {

            // Header (ligeramente más grande)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val context = LocalContext.current
                val imageLoader = remember {
                    ImageLoader.Builder(context).components {
                        if (Build.VERSION.SDK_INT >= 28) add(ImageDecoderDecoder.Factory())
                        else add(GifDecoder.Factory())
                    }.build()
                }
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFF8B5CF6)),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = R.drawable.stuffies_anim,
                        imageLoader = imageLoader,
                        contentDescription = "Logo animado",
                        modifier = Modifier.matchParentSize(),
                        contentScale = ContentScale.Fit
                    )
                }

                Spacer(Modifier.width(12.dp))

                Text(
                    text = "STUFFIES",
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 26.sp
                )

                Spacer(Modifier.weight(1f))

                OutlinedButton(
                    onClick = { /* navegar a productos */ },
                    shape = RoundedCornerShape(12.dp),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
                ) { Text("Productos") }
            }

            Spacer(Modifier.height(16.dp))

            // HERO (más alto que en compact)
            Card(
                shape = RoundedCornerShape(22.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.linearGradient(listOf(Color(0xFF0EA5E9), Color(0xFF22D3EE)))
                        )
                        .padding(22.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Column {
                            Text(
                                "ROPA URBANA CON ESTILO",
                                color = Color.White,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 22.sp
                            )
                            Spacer(Modifier.height(6.dp))
                            Text(
                                "Poleras, polerones, jeans y accesorios con vibra estadounidense.",
                                color = Color(0xFFECFEFF),
                                fontSize = 14.sp
                            )
                        }
                        Button(
                            onClick = onVerProductos,
                            shape = RoundedCornerShape(16.dp)
                        ) { Text("Ver productos") }
                    }
                }
            }
        }

        Spacer(Modifier.width(20.dp))

        // DERECHA: Promos + About + Footer
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(8.dp))

            Text(
                "Promociones",
                style = MaterialTheme.typography.titleLarge,
                color = Color(0xFFE5E7EB),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))

            Row(Modifier.fillMaxWidth()) {
                Card(
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Box(
                        Modifier
                            .height(110.dp)
                            .background(Color(0xFF0EA5E9))
                    ) {
                        Text(
                            "Promo Poleras",
                            color = Color.White,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(8.dp)
                        )
                    }
                }
                Spacer(Modifier.width(12.dp))
                Card(
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Box(
                        Modifier
                            .height(110.dp)
                            .background(Color(0xFF22D3EE))
                    ) {
                        Text(
                            "Promo Polerones",
                            color = Color.White,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(8.dp)
                        )
                    }
                }
            }

            Spacer(Modifier.height(20.dp))

            Card(shape = RoundedCornerShape(18.dp), modifier = Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp)) {
                    Text(
                        "Stuffies - Moda Urbana Chilena",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        "Desde junio 2024 creamos ropa moderna con estilo estadounidense para que cualquiera vista a la última.",
                        color = Color(0xFF4B5563),
                        lineHeight = 18.sp
                    )
                    Spacer(Modifier.height(8.dp))
                    OutlinedButton(onClick = onIrContacto, shape = RoundedCornerShape(14.dp)) {
                        Text("Contacto")
                    }
                }
            }

            Spacer(Modifier.height(8.dp))
            Text(
                "© 2025 Stuffies — Grupo 6",
                color = Color(0xFFE5E7EB),
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 800, heightDp = 480)
@Composable
fun PreviewHomeMedium() { HomeMedium() }

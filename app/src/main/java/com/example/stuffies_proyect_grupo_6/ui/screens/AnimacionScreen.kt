package com.example.stuffies_proyect_grupo_6.ui.screens

import android.os.Build
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest

// 🔗 GIF directo
private const val URL_STUFFIES_GIF =
    "https://i.postimg.cc/ZRJmDW8M/AQO8ai-RXoga41z-C0-ZC4-N-US9-TBe-Cszq-Lmv-K2-Jz-Mk-Kvm-CXbx-Pvka-P06-Nej-SV6b4d8-JUj-Az-Ez67pq8-ASTRHVp1l-CKBf4-Pdvv-Pa-Y-td.gif"

@Composable
fun AnimacionScreen() {
    OnlyGif()
}

@Composable
private fun OnlyGif() {
    val ctx = LocalContext.current

    // ⚙️ Forzamos decodificador GIF (Coil)
    val imageLoader = ImageLoader.Builder(ctx).components {
        if (Build.VERSION.SDK_INT >= 28) {
            add(ImageDecoderDecoder.Factory()) // API 28+: animaciones GIF/WEBP
        } else {
            add(GifDecoder.Factory())          // API < 28
        }
    }.build()

    // Solo el GIF, a lo ancho
    AsyncImage(
        model = ImageRequest.Builder(ctx)
            .data(URL_STUFFIES_GIF)
            .build(),
        imageLoader = imageLoader,
        contentDescription = "GIF Stuffies",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clip(RoundedCornerShape(18.dp))
    )
}

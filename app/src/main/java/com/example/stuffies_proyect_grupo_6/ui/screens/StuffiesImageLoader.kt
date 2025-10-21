package com.example.stuffies_proyect_grupo_6.core

import android.content.Context
import android.os.Build
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder

fun provideImageLoader(context: Context): ImageLoader =
    ImageLoader.Builder(context).components {
        if (Build.VERSION.SDK_INT >= 28) add(ImageDecoderDecoder.Factory())
        else add(GifDecoder.Factory())
    }.build()

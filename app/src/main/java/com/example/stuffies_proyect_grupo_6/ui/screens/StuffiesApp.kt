// app/src/main/java/com/example/stuffies_proyect_grupo_6/StuffiesApp.kt
package com.example.stuffies_proyect_grupo_6

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import com.example.stuffies_proyect_grupo_6.core.provideImageLoader

class StuffiesApp : Application(), ImageLoaderFactory {
    private val loader by lazy { provideImageLoader(this) }
    override fun newImageLoader(): ImageLoader = loader
}

package com.example.stuffies_proyect_grupo_6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.stuffies_proyect_grupo_6.ui.screens.HomeAdaptive
import com.example.stuffies_proyect_grupo_6.utils.appWindowSizeClass

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val windowSize = appWindowSizeClass()
            HomeAdaptive(windowSize)
        }
    }
}

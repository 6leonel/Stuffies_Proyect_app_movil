package com.example.stuffies_proyect_grupo_6.ui.screens

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable

@Composable
fun HomeAdaptive(windowSize: WindowSizeClass) {
    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact  -> HomeCompact()
        WindowWidthSizeClass.Medium   -> HomeMedium()
        WindowWidthSizeClass.Expanded -> HomeExpanded()
        else -> HomeCompact()
    }
}

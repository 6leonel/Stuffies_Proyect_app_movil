package com.example.stuffies_proyect_grupo_6.ui.screens


import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable

@Composable
fun HomeAdaptive(
    windowSize: WindowSizeClass,
    onGoProfile: () -> Unit,
    onGoSettings: () -> Unit
) {
    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact  -> HomeCompact(onGoProfile = onGoProfile, onGoSettings = onGoSettings)
        WindowWidthSizeClass.Medium   -> HomeMedium (onGoProfile = onGoProfile, onGoSettings = onGoSettings)
        WindowWidthSizeClass.Expanded -> HomeExpanded(onGoProfile = onGoProfile, onGoSettings = onGoSettings)
        else -> HomeCompact(onGoProfile = onGoProfile, onGoSettings = onGoSettings)
    }
}
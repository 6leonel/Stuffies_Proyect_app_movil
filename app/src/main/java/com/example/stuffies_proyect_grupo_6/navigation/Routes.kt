package com.example.stuffies_proyect_grupo_6.navigation

sealed class Route(val path: String) {
    data object Home : Route("home")
    data object Profile : Route("profile")
    data object Settings : Route("settings")
}

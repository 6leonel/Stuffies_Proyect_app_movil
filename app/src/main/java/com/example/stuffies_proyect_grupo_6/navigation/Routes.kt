package com.example.stuffies_proyect_grupo_6.navigation

sealed class Route(val path: String) {
    object Home    : Route("home")
    object Profile : Route("profile")
    object Settings: Route("settings")
    object Registro: Route("registro")   // si no los usas ahora, igual pueden quedar
    object Resumen : Route("resumen")
}

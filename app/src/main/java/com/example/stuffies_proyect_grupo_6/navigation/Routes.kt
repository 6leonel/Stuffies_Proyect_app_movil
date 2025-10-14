package com.example.stuffies_proyect_grupo_6.navigation

sealed class Route(val path: String) {
    data object Home : Route("home")
    data object Productos : Route("productos")
    data object Blogs : Route("blogs")
    data object Nosotros : Route("nosotros")
    data object Animacion : Route("animacion")
    data object Contacto : Route("contacto")
    data object Carrito : Route("carrito")
    data object Login : Route("login")
    data object Perfil : Route("perfil")
    data object Registro : Route("registro")
    data object Resumen : Route("resumen")
    data object Settings : Route("settings")

    // 🔹 Ruta con argumento (ej: detalle de producto)
    data object ProductoDetalle : Route("producto/{id}") {
        fun create(id: Int) = "producto/$id"
    }
}

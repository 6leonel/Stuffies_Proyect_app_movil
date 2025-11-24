package com.example.stuffies_proyect_grupo_6.model

/**
 * Modelo que representa un producto devuelto por el microservicio Stuffies.
 * Debe calzar con las claves del JSON.
 */
data class Post(
    val id: Int,
    val nombre: String,
    val precio: Int,
    val categoria: String,
    val imagen: String,
    val imagenHover: String,
    val descripcion: String,
    val tallas: List<String>,
    val colores: List<String>,
    val destacado: Boolean
)

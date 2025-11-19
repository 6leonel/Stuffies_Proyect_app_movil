package com.example.stuffies_proyect_grupo_6.model

/**
 * Modelo que representa un producto del microservicio Spring Boot.
 * AJUSTA los campos según lo que devuelva tu backend.
 */
data class Post(
    val id: Int,
    val nombre: String,
    val precio: Int,
    val categoria: String,
    val imagen: String? = null,
    val descripcion: String? = null
)

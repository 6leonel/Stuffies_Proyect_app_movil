package com.example.stuffies_proyect_grupo_6.data.model

/**
 * Modelo que representa el JSON que entrega el microservicio de Spring Boot.
 *
 * AJUSTA estos campos a tu DTO real del backend.
 */
data class Post(
    val id: Int,
    val nombre: String,
    val precio: Int,
    val categoria: String,
    val imagen: String? = null,
    val descripcion: String? = null
)

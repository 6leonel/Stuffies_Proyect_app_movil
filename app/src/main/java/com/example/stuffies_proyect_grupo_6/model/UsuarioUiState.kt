package com.example.stuffies_proyect_grupo_6.model

data class UsuarioUiState(
    val nombre: String = "",
    val correo: String = "",
    val clave: String = "",
    val direccion: String = "",
    val aceptaTerminos: Boolean = false,
    // 👇 ESTE campo debe existir para poder hacer it.copy(errores = …)
    val errores: UsuarioErrores = UsuarioErrores()
)

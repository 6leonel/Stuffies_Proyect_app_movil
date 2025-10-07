package com.example.stuffies_proyect_grupo_6.viewmodel

import android.util.Patterns
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class UsuarioUiState(
    val nombre: String = "",
    val correo: String = "",
    val clave: String = "",
    val direccion: String = "",
    val aceptaTerminos: Boolean = false
)

data class UsuarioErrores(
    val nombre: String? = null,
    val correo: String? = null,
    val clave: String? = null,
    val direccion: String? = null,
    val aceptaTerminos: String? = null
)

class UsuarioViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UsuarioUiState())
    val uiState: StateFlow<UsuarioUiState> = _uiState.asStateFlow()

    private val _errores = MutableStateFlow(UsuarioErrores())
    val errores: StateFlow<UsuarioErrores> = _errores.asStateFlow()

    fun onNombreChange(v: String)    { _uiState.value = _uiState.value.copy(nombre = v) }
    fun onCorreoChange(v: String)    { _uiState.value = _uiState.value.copy(correo = v) }
    fun onClaveChange(v: String)     { _uiState.value = _uiState.value.copy(clave = v) }
    fun onDireccionChange(v: String) { _uiState.value = _uiState.value.copy(direccion = v) }
    fun onAceptaChange(v: Boolean)   { _uiState.value = _uiState.value.copy(aceptaTerminos = v) }

    fun validarFormulario(): Boolean {
        val s = _uiState.value
        val e = UsuarioErrores(
            nombre = if (s.nombre.isBlank()) "Ingresa tu nombre" else null,
            correo = when {
                s.correo.isBlank() -> "Ingresa tu correo"
                !Patterns.EMAIL_ADDRESS.matcher(s.correo).matches() -> "Correo inválido"
                else -> null
            },
            clave = when {
                s.clave.isBlank() -> "Ingresa una clave"
                s.clave.length < 6 -> "Debe tener al menos 6 caracteres"
                else -> null
            },
            direccion = if (s.direccion.isBlank()) "Ingresa tu dirección" else null,
            aceptaTerminos = if (!s.aceptaTerminos) "Debes aceptar los términos" else null
        )
        _errores.value = e
        return listOf(e.nombre, e.correo, e.clave, e.direccion, e.aceptaTerminos).all { it == null }
    }

    fun limpiarErrores() { _errores.value = UsuarioErrores() }
}

package com.example.stuffies_proyect_grupo_6.viewmodel

import androidx.lifecycle.ViewModel
import com.example.stuffies_proyect_grupo_6.model.UsuarioErrores
import com.example.stuffies_proyect_grupo_6.model.UsuarioUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class UsuarioViewModel : ViewModel() {

    private val _estado = MutableStateFlow(value = UsuarioUiState())
    val estado: StateFlow<UsuarioUiState> = _estado

    fun onNombreChange(valor: String) {
        _estado.update { it.copy(nombre = valor, errores = it.errores.copy(nombre = null)) }
    }
    fun onCorreoChange(valor: String) {
        _estado.update { it.copy(correo = valor, errores = it.errores.copy(correo = null)) }
    }
    fun onClaveChange(valor: String) {
        _estado.update { it.copy(clave = valor, errores = it.errores.copy(clave = null)) }
    }
    fun onDireccionChange(valor: String) {
        _estado.update { it.copy(direccion = valor, errores = it.errores.copy(direccion = null)) }
    }
    fun onAceptaTerminosChange(valor: Boolean) {
        _estado.update { it.copy(aceptaTerminos = valor, errores = it.errores.copy(aceptaTerminos = null)) }
    }

    fun validarFormulario(): Boolean {
        val s = _estado.value
        val errores = UsuarioErrores(
            nombre = if (s.nombre.isBlank()) "Campo obligatorio" else null,
            correo = if (!s.correo.contains("@")) "Correo inválido" else null,
            clave = if (s.clave.length < 6) "Debe tener al menos 6 caracteres" else null,
            direccion = if (s.direccion.isBlank()) "Campo obligatorio" else null,
            aceptaTerminos = if (!s.aceptaTerminos) "Debes aceptar términos" else null
        )
        val hayErrores = listOfNotNull(
            errores.nombre, errores.correo, errores.clave, errores.direccion, errores.aceptaTerminos
        ).isNotEmpty()

        _estado.update { it.copy(errores = errores) }
        return !hayErrores
    }
}

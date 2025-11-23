package com.example.stuffies_proyect_grupo_6.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * Estado de la pantalla de usuario (formulario de registro).
 */
data class UsuarioErrores(
    val nombre: String? = null,
    val correo: String? = null,
    val clave: String? = null,
    val direccion: String? = null,
    val aceptaTerminos: String? = null
)

data class UsuarioUiState(
    val nombre: String = "",
    val correo: String = "",
    val clave: String = "",
    val direccion: String = "",
    val aceptaTerminos: Boolean = false,
    val errores: UsuarioErrores = UsuarioErrores()
)

/**
 * ViewModel usado para la validación del formulario de usuario.
 *
 * IMPORTANTE:
 *  - La validación del correo ahora es SIMPLE:
 *      - no vacío
 *      - contiene "@"
 *    Esto hace que correos como ".120@gmail.com" sean aceptados,
 *    igual que en RegistroScreen.validar().
 */
class UsuarioViewModel : ViewModel() {

    private val _estado = MutableStateFlow(UsuarioUiState())
    val estado: StateFlow<UsuarioUiState> = _estado.asStateFlow()

    // ---- Setters de campos (se usan desde RegistroScreen) ----

    fun onNombreChange(valor: String) {
        _estado.update { actual ->
            actual.copy(
                nombre = valor,
                errores = actual.errores.copy(nombre = null)
            )
        }
    }

    fun onCorreoChange(valor: String) {
        _estado.update { actual ->
            actual.copy(
                correo = valor,
                errores = actual.errores.copy(correo = null)
            )
        }
    }

    fun onClaveChange(valor: String) {
        _estado.update { actual ->
            actual.copy(
                clave = valor,
                errores = actual.errores.copy(clave = null)
            )
        }
    }

    fun onDireccionChange(valor: String) {
        _estado.update { actual ->
            actual.copy(
                direccion = valor,
                errores = actual.errores.copy(direccion = null)
            )
        }
    }

    fun onAceptaTerminosChange(valor: Boolean) {
        _estado.update { actual ->
            actual.copy(
                aceptaTerminos = valor,
                errores = actual.errores.copy(aceptaTerminos = null)
            )
        }
    }

    // ---- Validación del formulario (usada en RegistroScreen.onCrearCuenta) ----

    /**
     * Valida el formulario completo y actualiza los mensajes de error en el estado.
     *
     * Devuelve:
     *  - true  -> si todos los campos son válidos
     *  - false -> si hay al menos un error
     */
    fun validarFormulario(): Boolean {
        val actual = _estado.value

        var errores = UsuarioErrores()
        var esValido = true

        // Nombre obligatorio
        if (actual.nombre.isBlank()) {
            errores = errores.copy(nombre = "Ingresa tu nombre.")
            esValido = false
        }

        // CORREO SIMPLE:
        //  - no vacío
        //  - debe contener "@"
        if (actual.correo.isBlank() || !actual.correo.contains("@")) {
            errores = errores.copy(correo = "Correo inválido.")
            esValido = false
        }

        // Clave entre 4 y 10 caracteres
        if (actual.clave.length !in 4..10) {
            errores = errores.copy(clave = "La contraseña debe tener entre 4 y 10 caracteres.")
            esValido = false
        }

        // Dirección obligatoria
        if (actual.direccion.isBlank()) {
            errores = errores.copy(direccion = "Ingresa tu dirección.")
            esValido = false
        }

        // Aceptar términos (si lo usas en alguna pantalla)
        if (!actual.aceptaTerminos) {
            errores = errores.copy(aceptaTerminos = "Debes aceptar los términos.")
            esValido = false
        }

        // Actualizamos el estado con los errores
        _estado.value = actual.copy(errores = errores)

        return esValido
    }
}

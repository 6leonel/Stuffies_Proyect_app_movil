package com.example.stuffies_proyect_grupo_6.viewmodel

import org.junit.Assert.*
import org.junit.Test

class UsuarioViewModelTest {

    @Test
    fun validarFormulario_camposVacios_retornaFalse_ySeteaErrores() {
        // GIVEN
        val vm = UsuarioViewModel()

        // No seteamos nada: todo viene vacío por defecto en UsuarioUiState()

        // WHEN
        val esValido = vm.validarFormulario()

        // THEN
        assertFalse(esValido)

        val errores = vm.estado.value.errores

        assertEquals("Campo obligatorio", errores.nombre)
        assertEquals("Correo inválido", errores.correo)
        assertEquals("Debe tener al menos 6 caracteres", errores.clave)
        assertEquals("Campo obligatorio", errores.direccion)
        assertEquals("Debes aceptar términos", errores.aceptaTerminos)
    }

    @Test
    fun validarFormulario_datosValidos_retornaTrue_yLimpiaErrores() {
        // GIVEN
        val vm = UsuarioViewModel()

        vm.onNombreChange("Smoke Duoc")
        vm.onCorreoChange("smoke@example.cl")
        vm.onClaveChange("123456")
        vm.onDireccionChange("Calle Falsa 123")
        vm.onAceptaTerminosChange(true)

        // WHEN
        val esValido = vm.validarFormulario()

        // THEN
        assertTrue(esValido)

        val errores = vm.estado.value.errores

        assertNull(errores.nombre)
        assertNull(errores.correo)
        assertNull(errores.clave)
        assertNull(errores.direccion)
        assertNull(errores.aceptaTerminos)
    }
}

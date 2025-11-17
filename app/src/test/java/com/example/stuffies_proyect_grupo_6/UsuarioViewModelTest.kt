package com.example.stuffies_proyect_grupo_6

import com.example.stuffies_proyect_grupo_6.viewmodel.UsuarioViewModel
import org.junit.Assert.*
import org.junit.Test

class UsuarioViewModelTest {

    @Test
    fun validarFormulario_campos_vacios_retorna_false_y_setea_errores() {
        val vm = UsuarioViewModel()

        val esValido = vm.validarFormulario()

        assertFalse(esValido)

        val errores = vm.estado.value.errores
        assertEquals("Campo obligatorio", errores.nombre)
        assertEquals("Correo inválido", errores.correo)
        assertEquals("Debe tener al menos 6 caracteres", errores.clave)
        assertEquals("Campo obligatorio", errores.direccion)
        assertEquals("Debes aceptar términos", errores.aceptaTerminos)
    }

    @Test
    fun validarFormulario_datos_validos_retorna_true_y_sin_errores() {
        val vm = UsuarioViewModel()

        vm.onNombreChange("Smoke Duoc")
        vm.onCorreoChange("smoke@example.cl")
        vm.onClaveChange("123456")
        vm.onDireccionChange("Calle Falsa 123")
        vm.onAceptaTerminosChange(true)

        val esValido = vm.validarFormulario()

        assertTrue(esValido)

        val errores = vm.estado.value.errores
        assertNull(errores.nombre)
        assertNull(errores.correo)
        assertNull(errores.clave)
        assertNull(errores.direccion)
        assertNull(errores.aceptaTerminos)
    }
}

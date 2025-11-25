package com.example.stuffies_proyect_grupo_6

import com.example.stuffies_proyect_grupo_6.viewmodel.UsuarioViewModel
import org.junit.Assert.*
import org.junit.Test

class UsuarioViewModelTest {

    // 1) Formulario vacío → false + todos los errores seteados
    @Test
    fun validarFormulario_camposVacios_retornaFalse_ySeteaErrores() {
        val vm = UsuarioViewModel()

        val esValido = vm.validarFormulario()

        assertFalse(esValido)

        val errores = vm.estado.value.errores
        assertEquals("Ingresa tu nombre.", errores.nombre)
        assertEquals("Correo inválido.", errores.correo)
        assertEquals("La contraseña debe tener entre 4 y 10 caracteres.", errores.clave)
        assertEquals("Ingresa tu dirección.", errores.direccion)       // 👈 CAMBIADO
        assertEquals("Debes aceptar los términos.", errores.aceptaTerminos)

    }

    // 2) Datos válidos → true + sin errores
    @Test
    fun validarFormulario_datosValidos_retornaTrue_ySinErrores() {
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

    // 3) onNombreChange actualiza nombre y limpia solo su error
    @Test
    fun onNombreChange_actualizaNombre_yLimpiaErrorNombre() {
        val vm = UsuarioViewModel()

        // fuerza un error primero
        vm.validarFormulario()
        // 👇 mensaje real
        assertEquals("Ingresa tu nombre.", vm.estado.value.errores.nombre)

        // ahora cambia el nombre
        vm.onNombreChange("Leonel")

        val estado = vm.estado.value
        assertEquals("Leonel", estado.nombre)
        assertNull(estado.errores.nombre)
    }

    // 4) Correo sin @ → solo error de correo
    @Test
    fun validarFormulario_correoInvalido_seteaSoloErrorCorreo() {
        val vm = UsuarioViewModel()

        vm.onNombreChange("Usuario")
        vm.onCorreoChange("correomalo.cl")
        vm.onClaveChange("123456")
        vm.onDireccionChange("Calle 1")
        vm.onAceptaTerminosChange(true)

        val esValido = vm.validarFormulario()

        assertFalse(esValido)
        val errores = vm.estado.value.errores
        assertEquals("Correo inválido.", errores.correo)  // 👈 con punto
        assertNull(errores.nombre)
        assertNull(errores.clave)
        assertNull(errores.direccion)
        assertNull(errores.aceptaTerminos)
    }

    // 5) Clave corta → error solo en clave
    @Test
    fun validarFormulario_claveCorta_seteaErrorClave() {
        val vm = UsuarioViewModel()

        vm.onNombreChange("Usuario")
        vm.onCorreoChange("mail@duoc.cl")
        vm.onClaveChange("123")     // corta
        vm.onDireccionChange("Calle 1")
        vm.onAceptaTerminosChange(true)

        val esValido = vm.validarFormulario()

        assertFalse(esValido)
        val errores = vm.estado.value.errores
        assertEquals("La contraseña debe tener entre 4 y 10 caracteres.", errores.clave)
        assertNull(errores.nombre)
        assertNull(errores.correo)
        assertNull(errores.direccion)
        assertNull(errores.aceptaTerminos)
    }

    // 6) No acepta términos → error solo en aceptaTerminos
    @Test
    fun validarFormulario_noAceptaTerminos_seteaErrorTerminos() {
        val vm = UsuarioViewModel()

        vm.onNombreChange("Usuario")
        vm.onCorreoChange("mail@duoc.cl")
        vm.onClaveChange("123456")
        vm.onDireccionChange("Calle 1")
        vm.onAceptaTerminosChange(false)  // no acepta

        val esValido = vm.validarFormulario()

        assertFalse(esValido)
        val errores = vm.estado.value.errores
        assertEquals("Debes aceptar los términos.", errores.aceptaTerminos)
        assertNull(errores.nombre)
        assertNull(errores.correo)
        assertNull(errores.clave)
        assertNull(errores.direccion)
    }
}

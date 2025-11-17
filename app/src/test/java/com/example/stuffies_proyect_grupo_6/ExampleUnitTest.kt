package com.example.stuffies_proyect_grupo_6

import com.example.stuffies_proyect_grupo_6.ui.screens.Catalogo
import com.example.stuffies_proyect_grupo_6.ui.screens.clp
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}

class ProductsRepoTest {

    @Test
    fun productoPorId_encuentra_producto_existente() {
        val producto = Catalogo.productoPorId(1)

        assertNotNull(producto)
        assertEquals(1, producto?.id)
        assertTrue(producto?.nombre?.isNotBlank() == true)
    }

    @Test
    fun categorias_siempre_incluye_todos_como_primer_elemento() {
        val categorias = Catalogo.categorias()

        assertFalse(categorias.isEmpty())
        assertEquals("todos", categorias.first())
        assertEquals(categorias.size, categorias.distinct().size) // sin duplicados
    }

    @Test
    fun clp_formatea_el_precio_en_pesos_chilenos() {
        val formateado = clp(39990)

        assertTrue(formateado.startsWith("$"))
        assertTrue(formateado.contains("39"))
    }
}
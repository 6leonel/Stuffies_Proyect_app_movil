package com.example.stuffies_proyect_grupo_6.ui.screens

import java.text.NumberFormat
import java.util.Locale

data class Producto(
    val id: Int,
    val nombre: String,
    val precio: Int,
    val categoria: String,
    val imagen: String,
    val imagenHover: String? = null,
    val descripcion: String = "",
    val tallas: List<String> = emptyList(),
    val colores: List<String> = emptyList(),
    val destacado: Boolean = false
)

object Catalogo {
    val productos: List<Producto> = listOf(
        Producto(
            id = 1,
            nombre = "HOODIE BOXIFIT WHITE DICE V2",
            precio = 39990,
            categoria = "polerones",
            imagen = "https://stuffiesconcept.com/cdn/shop/files/WhiteDice1.png?v=1753404231&width=600",
            imagenHover = "https://stuffiesconcept.com/cdn/shop/files/WhiteDice2.png?v=1753404231&width=1426",
            descripcion = "POLERÓN BOXIFIT AZUL MARINO 'WHITE DICE'.",
            tallas = listOf("S","M","L","XL"),
            colores = listOf("azul"),
            destacado = false
        ),
        Producto(
            id = 2,
            nombre = "Star Player 'Blue Team' T-Shirt",
            precio = 10990,
            categoria = "poleras",
            imagen = "https://stuffiesconcept.com/cdn/shop/files/1_594f01e1-55e5-4516-b0af-d2befc1aa113.png?v=1748653006&width=600",
            imagenHover = "https://stuffiesconcept.com/cdn/shop/files/2_221c9cfc-6049-4eb1-b7ec-3b19bd755c48.png?v=1748653006&width=600",
            descripcion = "La Star Player T-Shirt nace de la unión entre la nostalgia del fútbol clásico y la energía del streetwear actual.",
            tallas = listOf("M","L","XL"),
            colores = listOf("diseño unico"),
            destacado = false
        ),
        Producto(
            id = 3,
            nombre = "Stella Chroma Zip Hoodie",
            precio = 55990,
            categoria = "polerones",
            imagen = "https://stuffiesconcept.com/cdn/shop/files/1_8ee3f1b2-2f8a-45ba-bb78-a2f4ba49c4d5.png?v=1756936574&width=600",
            imagenHover = "https://stuffiesconcept.com/cdn/shop/files/2_1c0d6df0-c713-49a3-b2bd-b07d19c392ee.png?v=1756936574&width=600",
            descripcion = "Hoodie con cierre frontal y bolsillos.",
            tallas = listOf("S","M","L","XL"),
            colores = listOf("Negro"),
            destacado = true
        ),
        Producto(
            id = 4,
            nombre = "Stella Boxy-Slim White Tee",
            precio = 22990,
            categoria = "poleras",
            imagen = "https://stuffiesconcept.com/cdn/shop/files/3_0f38dc89-f9f8-4998-be22-b2e0122e8816.png?v=1756936601&width=600",
            imagenHover = "https://stuffiesconcept.com/cdn/shop/files/4_8a500939-3d78-4b9c-aaab-fc34db0d117d.png?v=1756936601&width=600",
            descripcion = "Camiseta blanca corte boxy-slim.",
            tallas = listOf("S","M","L","XL"),
            colores = listOf("Blanco"),
            destacado = false
        ),
        Producto(
            id = 5,
            nombre = "Stella Boxy-Slim Black Tee",
            precio = 15990,
            categoria = "poleras ",
            imagen = "https://stuffiesconcept.com/cdn/shop/files/5.png?v=1756936590&width=493",
            imagenHover = "https://stuffiesconcept.com/cdn/shop/files/6.png?v=1756936591&width=493",
            descripcion = "Polera boxy-slim fit negra",
            tallas = listOf("S","M","L","XL"),
            colores = listOf("Negro"),
            destacado = true
        ),
        Producto(
            id = 6,
            nombre = "HOODIE BOXIFIT RED DICE V2",
            precio = 32990,
            categoria = "polerones",
            imagen = "https://stuffiesconcept.com/cdn/shop/files/RedDice1.png?v=1753404319&width=600",
            imagenHover = "https://stuffiesconcept.com/cdn/shop/files/RedDice2.png?v=1753404319&width=600",
            descripcion = "Chaqueta ligera, perfecta para entrenar.",
            tallas = listOf("S","M","L","XL"),
            colores = listOf("Negro"),
            destacado = true
        ),
        Producto(
            id = 7,
            nombre = "Star Player 'Black Team' t-shirt",
            precio = 37990,
            categoria = "poleras",
            imagen = "https://stuffiesconcept.com/cdn/shop/files/3_f5bf3ad8-c122-436f-8eee-1483a3f383da.png?v=1748652948&width=600",
            imagenHover = "https://stuffiesconcept.com/cdn/shop/files/4_b9bc3afc-97e9-4636-94f4-1a863738d755.png?v=1748652948&width=600",
            descripcion = "La Star Player T-Shirt nace de la unión entre la nostalgia del fútbol clásico y la energía del streetwear actual..",
            tallas = listOf("S","M","L","XL"),
            colores = listOf("Gris"),
            destacado = true
        ),
        Producto(
            id = 8,
            nombre = "HOODIE BOXIFIT PINK DICE V2",
            precio = 35990,
            categoria = "polerones",
            imagen = "https://stuffiesconcept.com/cdn/shop/files/PinkDice1.png?v=1753404299&width=600",
            imagenHover = "https://stuffiesconcept.com/cdn/shop/files/PinkDice2.png?v=1753404299&width=600",
            descripcion = "POLERÓN BOXIFIT BROWN 'PINK DICE'",
            tallas = listOf("S","M","L","XL"),
            colores = listOf("cafe"),
            destacado = false
        )
    )

    fun productoPorId(id: Int) = productos.find { it.id == id }

    fun categorias(): List<String> =
        listOf("todos") + productos.map { it.categoria.trim() }.distinct()
}

private val clpNumber: NumberFormat = NumberFormat.getInstance(Locale("es", "CL")).apply {
    maximumFractionDigits = 0
}
fun clp(valor: Int): String = "$${clpNumber.format(valor)}"

@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example.stuffies_proyect_grupo_6.ui.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Locale

/* ====== Estilos ====== */
private val BlackBg  = Color(0xFF000000)
private val GrayText = Color(0xFFBFBFBF)
private val CardWhite = Color(0xFFFFFFFF)

/* ====== CLP ====== */
private val CLP: NumberFormat = NumberFormat.getInstance(Locale("es", "CL"))
private fun Long.clp(): String = CLP.format(this)

/* Conversión segura a Long desde distintos tipos (Int, Long, String) */
@Suppress("UNCHECKED_CAST")
private fun priceToLong(any: Any?): Long = when (any) {
    null -> 0L
    is Long -> any
    is Int -> any.toLong()
    is Double -> any.toLong()
    is Float -> any.toLong()
    is String -> any
        .replace(".", "")
        .replace(",", "")
        .trim()
        .toLongOrNull() ?: 0L
    else -> 0L
}

/* ====== Fuente de datos ======
   Usa tu lista global del Home: Catalogo.productos
*/
private fun getProductoById(id: Int): Producto? =
    try { Catalogo.productos.firstOrNull { it.id == id } }
    catch (_: Throwable) { null }

/* ====== “LocalStorage” del carrito (mismo formato que CarritoScreen) ====== */
private const val CART_PREFS = "stuffies_cart"
private const val CART_KEY   = "carrito_raw"
private const val SEP        = "\u001F"

private fun loadCart(ctx: Context): MutableList<List<String>> {
    val raw = ctx.getSharedPreferences(CART_PREFS, Context.MODE_PRIVATE)
        .getString(CART_KEY, "") ?: ""
    if (raw.isBlank()) return mutableListOf()
    return raw.lineSequence()
        .filter { it.isNotBlank() }
        .map { it.split(SEP) } // [id, nombre, precio, cantidad, imagen, talla, color]
        .toMutableList()
}

private fun saveCart(ctx: Context, rows: List<List<String>>) {
    val raw = buildString {
        rows.forEach { cols ->
            append(cols.joinToString(SEP))
            append('\n')
        }
    }
    ctx.getSharedPreferences(CART_PREFS, Context.MODE_PRIVATE)
        .edit().putString(CART_KEY, raw).apply()
}

private fun addToCart(
    ctx: Context,
    id: Int?,
    nombre: String,
    precio: Long,
    cantidad: Int,
    imagen: String,
    talla: String,
    color: String
) {
    val rows = loadCart(ctx)
    val idx = rows.indexOfFirst { cols ->
        val sameId = (cols.getOrNull(0)?.toIntOrNull() == id)
        val sameTalla = cols.getOrNull(5) == talla
        val sameColor = cols.getOrNull(6) == color
        sameId && sameTalla && sameColor
    }
    if (idx >= 0) {
        val r = rows[idx].toMutableList()
        val oldCant = r.getOrNull(3)?.toIntOrNull() ?: 1
        r[3] = (oldCant + cantidad).toString()
        rows[idx] = r
    } else {
        rows.add(listOf(
            id?.toString() ?: "",
            nombre,
            precio.toString(),
            cantidad.toString(),
            imagen,
            talla,
            color
        ))
    }
    saveCart(ctx, rows)
}

/* =================== PANTALLA =================== */
@Composable
fun ProductoDetalleScreen(id: Int) {
    val ctx = LocalContext.current
    val snackbar = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val p = remember(id) { getProductoById(id) }

    // Derivar precio como Long seguro
    val priceL = remember(p) { priceToLong(p?.precio) }

    // Si tu data class Producto NO tiene tallas/colores, mantenemos defaults.
    var tallaSel by remember { mutableStateOf(p?.tallas?.firstOrNull() ?: "Única") }
    var colorSel by remember { mutableStateOf(p?.colores?.firstOrNull() ?: "Único") }
    var cantidad by remember { mutableStateOf(1) }

    Scaffold(
        containerColor = BlackBg,
        contentColor = Color.White,
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = BlackBg,
                    titleContentColor = Color.White
                ),
                title = { Text("Detalle de producto") }
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbar) }
    ) { inner ->
        Column(
            modifier = Modifier
                .padding(inner)
                .fillMaxSize()
                .background(BlackBg)
                .verticalScroll(rememberScrollState())
        ) {
            if (p == null) {
                Text(
                    "Producto no encontrado",
                    color = Color.White,
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.titleLarge
                )
                return@Column
            }

            // Card blanca con el detalle
            Card(
                colors = CardDefaults.cardColors(containerColor = CardWhite),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .fillMaxWidth()
            ) {
                Column(Modifier.padding(16.dp)) {
                    // Imagen
                    AsyncImage(
                        model = p.imagen,
                        contentDescription = p.nombre,
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 220.dp)
                            .clip(RoundedCornerShape(12.dp)),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(Modifier.height(16.dp))

                    // Info
                    Text(p.nombre, color = Color.Black, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold)
                    Spacer(Modifier.height(6.dp))
                    Text("$${priceL.clp()}", color = Color.Black, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)

                    Spacer(Modifier.height(16.dp))

                    // Tallas
                    if (!p.tallas.isNullOrEmpty()) {
                        Text("Talla", color = Color.Black, fontWeight = FontWeight.SemiBold)
                        Spacer(Modifier.height(6.dp))
                        FlowRowWrap {
                            p.tallas.forEach { t ->
                                FilterChipWhite(
                                    text = t,
                                    selected = tallaSel == t,
                                    onClick = { tallaSel = t }
                                )
                            }
                        }
                        Spacer(Modifier.height(12.dp))
                    }

                    // Colores
                    if (!p.colores.isNullOrEmpty()) {
                        Text("Color", color = Color.Black, fontWeight = FontWeight.SemiBold)
                        Spacer(Modifier.height(6.dp))
                        FlowRowWrap {
                            p.colores.forEach { c ->
                                FilterChipWhite(
                                    text = c,
                                    selected = colorSel == c,
                                    onClick = { colorSel = c }
                                )
                            }
                        }
                        Spacer(Modifier.height(12.dp))
                    }

                    // Cantidad
                    Text("Cantidad", color = Color.Black, fontWeight = FontWeight.SemiBold)
                    Spacer(Modifier.height(6.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        OutlinedButton(onClick = { if (cantidad > 1) cantidad-- }) { Text("-") }
                        Spacer(Modifier.width(8.dp))
                        Text("$cantidad", color = Color.Black)
                        Spacer(Modifier.width(8.dp))
                        OutlinedButton(onClick = { cantidad++ }) { Text("+") }
                    }

                    Spacer(Modifier.height(16.dp))

                    // Agregar al carrito
                    Button(
                        onClick = {
                            addToCart(
                                ctx = ctx,
                                id = p.id,
                                nombre = p.nombre,
                                precio = priceL,
                                cantidad = cantidad,
                                imagen = p.imagen,
                                talla = tallaSel,
                                color = colorSel
                            )
                            scope.launch { snackbar.showSnackbar("Agregado al carrito") }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) { Text("Añadir al carrito") }
                }
            }

            // About preview
            Spacer(Modifier.height(8.dp))
            Divider(color = Color(0xFF1F2937))
            Spacer(Modifier.height(12.dp))
            Column(Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                Text(
                    "Stuffies - Moda Urbana Chilena",
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    "Desde nuestro lanzamiento en junio de 2024, nos dedicamos a crear ropa moderna y de calidad con estilo estadounidense para que todos puedan vestir a la última moda.",
                    color = GrayText
                )
            }

            Spacer(Modifier.height(20.dp))
        }
    }
}

/* ====== Chips / layout ====== */

@Composable
private fun FilterChipWhite(text: String, selected: Boolean, onClick: () -> Unit) {
    val fg = if (selected) Color.White else Color.Black
    OutlinedButton(
        onClick = onClick,
        colors = ButtonDefaults.outlinedButtonColors(contentColor = fg),
        border = ButtonDefaults.outlinedButtonBorder.copy(width = if (selected) 2.dp else 1.dp),
        modifier = Modifier
            .padding(end = 8.dp, bottom = 8.dp)
            .height(36.dp),
        shape = RoundedCornerShape(10.dp)
    ) { Text(text, color = fg) }
}

/** Row simple; si luego quieres wrap real, lo cambiamos a FlowRow de Accompanist. */
@Composable
private fun FlowRowWrap(content: @Composable RowScope.() -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        content()
    }
}

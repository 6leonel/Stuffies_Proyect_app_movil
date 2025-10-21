@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example.stuffies_proyect_grupo_6.ui.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import java.text.NumberFormat
import java.util.Locale
import kotlinx.coroutines.launch
import androidx.compose.ui.draw.clip



/* ---------- Colores “negro total” + card blanca ---------- */
private val BlackBg  = Color(0xFF000000)
private val GrayText = Color(0xFFBFBFBF)
private val CardWhite = Color(0xFFFFFFFF)

/* ---------- Modelo ---------- */
data class CartItem(
    val id: Int? = null,
    val nombre: String = "Producto",
    val precio: Long = 0L,    // CLP
    val cantidad: Int = 1,
    val imagen: String = "",
    val talla: String = "Única",
    val color: String = "Único"
)

/* ---------- “LocalStorage” simple con SharedPreferences ----------
   Formato: una línea por ítem, campos separados por \u001F (Unit Separator)
------------------------------------------------------------------- */
private object CartStore {
    private const val PREFS = "stuffies_cart"
    private const val KEY = "carrito_raw"
    private const val SEP = "\u001F"

    private fun prefs(ctx: Context) =
        ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE)

    fun load(ctx: Context): MutableList<CartItem> {
        val raw = prefs(ctx).getString(KEY, "") ?: ""
        if (raw.isBlank()) return mutableListOf()
        return raw
            .split("\n")
            .filter { it.isNotBlank() }
            .mapNotNull { line ->
                val p = line.split(SEP)
                try {
                    CartItem(
                        id       = p.getOrNull(0)?.toIntOrNull(),
                        nombre   = p.getOrNull(1) ?: "Producto",
                        precio   = p.getOrNull(2)?.toLongOrNull() ?: 0L,
                        cantidad = p.getOrNull(3)?.toIntOrNull() ?: 1,
                        imagen   = p.getOrNull(4) ?: "",
                        talla    = p.getOrNull(5) ?: "Única",
                        color    = p.getOrNull(6) ?: "Único"
                    )
                } catch (_: Throwable) { null }
            }.toMutableList()
    }

    fun save(ctx: Context, items: List<CartItem>) {
        val raw = buildString {
            items.forEach { it ->
                append(listOf(
                    it.id?.toString() ?: "",
                    it.nombre,
                    it.precio.toString(),
                    it.cantidad.toString(),
                    it.imagen,
                    it.talla,
                    it.color
                ).joinToString(SEP))
                append('\n')
            }
        }
        prefs(ctx).edit().putString(KEY, raw).apply()
    }

    fun clear(ctx: Context) {
        prefs(ctx).edit().remove(KEY).apply()
    }
}

/* ---------- Utilidades ---------- */
private val CLP: NumberFormat = NumberFormat.getInstance(Locale("es", "CL"))
private fun Long.clp(): String = CLP.format(this)

/* ---------- Pantalla ---------- */
@Composable
fun CarritoScreen() {
    val ctx = LocalContext.current
    val snackbar = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // Estado del carrito (persistente)
    var items by remember { mutableStateOf(CartStore.load(ctx)) }

    fun sync() = CartStore.save(ctx, items)
    fun total(): Long = items.fold(0L) { acc, it -> acc + it.precio * it.cantidad }

    Scaffold(
        containerColor = BlackBg,
        contentColor = Color.White,
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = BlackBg,
                    titleContentColor = Color.White
                ),
                title = { Text("Tu carrito") }
            )
        },
        snackbarHost = { SnackbarHost(snackbar) }
    ) { inner ->
        Column(
            modifier = Modifier
                .padding(inner)
                .fillMaxSize()
                .background(BlackBg)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            // Card blanca como en tu HTML
            Card(
                colors = CardDefaults.cardColors(containerColor = CardWhite),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(Modifier.padding(16.dp)) {
                    Text("🛒 Tu carrito", color = Color.Black, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold)
                    Spacer(Modifier.height(8.dp))

                    if (items.isEmpty()) {
                        Text("Tu carrito está vacío.", color = Color(0xFF000000).copy(alpha = 0.7f))
                    } else {
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            itemsIndexed(items, key = { idx, _ -> idx }) { index, it ->
                                CartRow(
                                    item = it,
                                    onInc = {
                                        items = items.toMutableList().apply {
                                            set(index, it.copy(cantidad = it.cantidad + 1))
                                        }
                                        sync()
                                    },
                                    onDec = {
                                        items = items.toMutableList().apply {
                                            val newCant = it.cantidad - 1
                                            if (newCant <= 0) removeAt(index)
                                            else set(index, it.copy(cantidad = newCant))
                                        }
                                        sync()
                                    },
                                    onDel = {
                                        items = items.toMutableList().apply { removeAt(index) }
                                        sync()
                                    }
                                )
                                Divider(color = Color(0xFFE5E7EB))
                            }
                        }
                    }

                    Spacer(Modifier.height(8.dp))

                    // Total + Checkout
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text("Total", color = Color.Black, style = MaterialTheme.typography.titleMedium)
                            Text("$${total().clp()}", color = Color.Black, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                        }
                        Button(
                            onClick = {
                                if (items.isEmpty()) {
                                    scope.launch { snackbar.showSnackbar("Tu carrito está vacío") }
                                } else {
                                    CartStore.clear(ctx)
                                    items = mutableListOf()
                                    scope.launch { snackbar.showSnackbar("¡Gracias por tu compra! 🛍️") }
                                }
                            }
                        ) { Text("Finalizar compra") }
                    }
                }
            }

            // Mini “about” como remate negro (opcional)
            Spacer(Modifier.height(16.dp))
            Text(
                "Stuffies - Moda Urbana Chilena",
                color = Color.White,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(Modifier.height(6.dp))
            Text(
                "Desde nuestro lanzamiento en 2024, creamos ropa moderna de calidad con estilo estadounidense.",
                color = GrayText
            )
        }
    }
}

/* ---------- Fila de producto ---------- */
@Composable
private fun CartRow(
    item: CartItem,
    onInc: () -> Unit,
    onDec: () -> Unit,
    onDel: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = item.imagen,
                contentDescription = item.nombre,
                modifier = Modifier
                    .size(70.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
            Spacer(Modifier.width(12.dp))
            Column {
                Text(item.nombre, color = Color.Black, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold)
                Text("Talla: ${item.talla} • Color: ${item.color}", color = Color(0xFF6B7280), style = MaterialTheme.typography.labelMedium)
                Text("$${item.precio.clp()} x ${item.cantidad}", color = Color.Black, style = MaterialTheme.typography.bodySmall)
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedButton(onClick = onDec, contentPadding = PaddingValues(horizontal = 10.dp)) { Text("-") }
            Spacer(Modifier.width(6.dp))
            OutlinedButton(onClick = onInc, contentPadding = PaddingValues(horizontal = 10.dp)) { Text("+") }
            Spacer(Modifier.width(6.dp))
            Button(colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDC2626)), onClick = onDel) {
                Text("Eliminar")
            }
        }
    }
}

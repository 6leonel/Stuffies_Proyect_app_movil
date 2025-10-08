package com.example.stuffies_proyect_grupo_6.ui.screens

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stuffies_proyect_grupo_6.viewmodel.ModoEspecialViewModel
import androidx.compose.ui.draw.alpha


@Composable
fun ModoEspecialSection(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current
) {
    val vm: ModoEspecialViewModel =
        viewModel(factory = ModoEspecialViewModel.factory(context))

    val loading by vm.loading.collectAsState()
    val persisted by vm.enabled.collectAsState()
    val savedOnce by vm.savedOnce.collectAsState()

    // Estado controlado localmente y guardable en recomposición
    var localEnabled by rememberSaveable(persisted) { mutableStateOf(persisted) }

    // Texto derivado del estado
    val label by remember(localEnabled) {
        derivedStateOf { if (localEnabled) "Modo especial ACTIVADO" else "Modo especial DESACTIVADO" }
    }

    // Color animado según estado
    val chipColor by animateColorAsState(
        targetValue = if (localEnabled) Color(0xFF6C55F6) else Color(0xFF444A66),
        animationSpec = tween(300), label = "chipColor"
    )

    // Opacidad del botón guardar (puro detalle visual)
    val saveAlpha by animateFloatAsState(
        targetValue = if (localEnabled != persisted) 1f else 0.6f,
        label = "saveAlpha"
    )

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        color = Color(0xFF1E2339),
        shape = MaterialTheme.shapes.large
    ) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {

            Text("Modo especial", color = Color.White, fontWeight = FontWeight.SemiBold)

            if (loading) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) { CircularProgressIndicator(color = Color.White) }
            } else {
                AssistChip(
                    onClick = {
                        localEnabled = !localEnabled
                        vm.toggleLocal()
                    },
                    label = { Text(label, color = Color.White) },
                    colors = AssistChipDefaults.assistChipColors(containerColor = chipColor)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = { vm.save() },
                        enabled = localEnabled != persisted, // solo si hay cambios
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF6C55F6),
                            contentColor = Color.White
                        )
                    ) { Text("Guardar", modifier = Modifier.alpha(saveAlpha)) }

                    OutlinedButton(
                        onClick = { localEnabled = persisted },
                        modifier = Modifier.weight(1f)
                    ) { Text("Revertir") }
                }

                // Mensaje de éxito animado
                AnimatedVisibility(
                    visible = savedOnce,
                    enter = fadeIn() + expandVertically(),
                    exit = fadeOut() + shrinkVertically()
                ) {
                    Text(
                        "Estado guardado ✅",
                        color = Color(0xFFB9F6CA),
                        modifier = Modifier.padding(top = 6.dp)
                    )
                }
            }
        }
    }
}

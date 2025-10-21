package com.example.stuffies_proyect_grupo_6.ui.screens

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.stuffies_proyect_grupo_6.data.LocationUtils
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng as GmsLatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.launch

/** Coordenadas de la tienda (ejemplo: Plaza de Armas, Santiago). Reemplaza por las reales. */
private val TIENDA = GmsLatLng(-33.4372, -70.6506)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapaScreen(onBack: () -> Unit = {}) {
    val ctx = LocalContext.current
    val locUtils = remember { LocationUtils(ctx) }

    var permisosOk by remember { mutableStateOf(locUtils.hasLocationPermission(ctx)) }
    var miUbicacion by remember { mutableStateOf<GmsLatLng?>(null) }

    // Launcher para pedir permisos de ubicación
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { res ->
        permisosOk = res[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
                res[Manifest.permission.ACCESS_COARSE_LOCATION] == true
    }

    // Cargar last known location cuando tengamos permisos
    LaunchedEffect(permisosOk) {
        if (permisosOk) {
            val last = locUtils.getLastKnownLocation()
            miUbicacion = last?.let { GmsLatLng(it.lat, it.lng) }
        }
    }

    // Cámara inicial centrada en la tienda
    val cameraState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(TIENDA, 15f)
    }

    // Necesario para llamar a animate() (suspend)
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Ubicación de la tienda") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Black,
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    TextButton(onClick = onBack) { Text("Atrás", color = Color.White) }
                }
            )
        },
        containerColor = Color.Black,
        contentColor = Color.White
    ) { inner ->
        Column(
            modifier = Modifier
                .padding(inner)
                .fillMaxSize()
                .background(Color.Black)
        ) {
            // Botones
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (!permisosOk) {
                    Button(onClick = {
                        launcher.launch(
                            arrayOf(
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION
                            )
                        )
                    }) { Text("Permitir ubicación") }
                } else {
                    Button(onClick = {
                        miUbicacion?.let { loc ->
                            scope.launch {
                                cameraState.animate(
                                    update = CameraUpdateFactory.newLatLngZoom(loc, 16f),
                                    durationMs = 600
                                )
                            }
                        }
                    }) { Text("Mi ubicación") }
                }

                OutlinedButton(onClick = {
                    scope.launch {
                        cameraState.animate(
                            update = CameraUpdateFactory.newLatLngZoom(TIENDA, 16f),
                            durationMs = 600
                        )
                    }
                }) { Text("Tienda") }
            }

            // Mapa
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraState,
                properties = MapProperties(
                    isMyLocationEnabled = permisosOk, // punto azul si hay permisos
                    mapType = MapType.NORMAL
                ),
                uiSettings = MapUiSettings(
                    zoomControlsEnabled = false,
                    myLocationButtonEnabled = false
                )
            ) {
                // Marcador tienda
                Marker(
                    state = MarkerState(position = TIENDA),
                    title = "Stuffies - Tienda",
                    snippet = "Estamos aquí 👋"
                )

                // Marcador opcional de tu ubicación (además del punto azul)
                miUbicacion?.let {
                    Marker(
                        state = MarkerState(position = it),
                        title = "Tú",
                        snippet = "Tu ubicación aproximada"
                    )
                }
            }
        }
    }
}

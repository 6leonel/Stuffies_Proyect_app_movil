@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example.stuffies_proyect_grupo_6.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

private val BlackBg = Color(0xFF000000)
private val BlackCard = Color(0xFF0D0D0D)
private val GrayText = Color(0xFFBFBFBF)
private val GraySoft = Color(0xFF1F2937)

@Composable
fun NosotrosScreen() {
    Scaffold(
        containerColor = BlackBg,
        contentColor = Color.White,
        topBar = {
            CenterAlignedTopAppBar(
                colors = androidx.compose.material3.TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = BlackBg,
                    titleContentColor = Color.White
                ),
                title = { Text("Nosotros") }
            )
        }
    ) { inner ->
        Column(
            modifier = Modifier
                .padding(inner)
                .fillMaxSize()
                .background(BlackBg)
                .verticalScroll(rememberScrollState())
                .navigationBarsPadding()
        ) {
            Hero()
            BodyCard()
            AboutPreview()
            Spacer(Modifier.height(20.dp))
        }
    }
}

@Composable
private fun Hero() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(BlackBg)
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        Text(
            "Quiénes somos",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Spacer(Modifier.height(6.dp))
        Text(
            "Tienda Stuffies creada desde 2024.",
            style = MaterialTheme.typography.bodyLarge,
            color = GrayText
        )
    }
}

@Composable
private fun BodyCard() {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        Column(Modifier.padding(24.dp)) {
            // Historia + imagen
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(Modifier.weight(1f)) {
                    Text("Nuestra historia", color = Color.Black, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold)
                    Spacer(Modifier.height(8.dp))
                    Text(
                        "Stuffies nace en Chile con la idea de ofrecer básicos de calidad y diseño fresco. " +
                                "Inspirados en la cultura urbana, trabajamos colecciones versátiles que combinan comodidad y estilo.",
                        color = Color(0xFF111111)
                    )
                    Spacer(Modifier.height(12.dp))
                    Text("Valores", color = Color.Black, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                    Spacer(Modifier.height(6.dp))
                    Bullet("Calidad y detalle en cada prenda")
                    Bullet("Diseño accesible para todos")
                    Bullet("Comunidad y expresión personal")
                }
                Spacer(Modifier.width(16.dp))
                AsyncImage(
                    model = "https://i.postimg.cc/qRdn8fDv/LOGO-ESTRELLA-SIMPLE-CON-ESTRELLITAS.png",
                    contentDescription = "Stuffies",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .widthIn(max = 320.dp)
                        .fillMaxWidth(0.42f)
                )
            }

            Divider(Modifier.padding(vertical = 16.dp), color = Color(0xFFE9ECEF))

            // Equipo / Tecnologías
            Row(Modifier.fillMaxWidth()) {
                Column(Modifier.weight(1f)) {
                    Text("Equipo", color = Color.Black, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                    Spacer(Modifier.height(6.dp))
                    BulletBold("Dirección Creativa:", " Stuffies Studio")
                    BulletBold("Desarrollo Web:", " Estudiantes DUOC")
                    BulletBold("Marketing:", " Comunidad Stuffies")
                }
                Spacer(Modifier.width(16.dp))
                Column(Modifier.weight(1f)) {
                    Text("Tecnologías", color = Color.Black, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                    Spacer(Modifier.height(6.dp))
                    Text(
                        "Kotlin, CSS, JavaScript, LocalStorage.",
                        color = Color(0xFF111111)
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            Row {
                Button(
                    onClick = { /* nav a productos */ },
                    colors = ButtonDefaults.buttonColors(),
                ) { Text("Ver productos") }

                Spacer(Modifier.width(8.dp))

                OutlinedButton(
                    onClick = { /* nav a contacto */ },
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black),
                    border = ButtonDefaults.outlinedButtonBorder.copy(width = 1.dp)
                ) { Text("Contáctanos") }
            }
        }
    }
    Spacer(Modifier.height(16.dp))
}

@Composable
private fun AboutPreview() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(BlackBg)
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        Divider(color = GraySoft)
        Spacer(Modifier.height(12.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.weight(1f)) {
                Text(
                    "Stuffies - Moda Urbana Chilena",
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    "Desde nuestro lanzamiento en junio de 2024, nos dedicamos a crear ropa moderna y de calidad con estilo estadounidense para que todos puedan vestir a la última moda.",
                    color = GrayText
                )
            }
            Spacer(Modifier.width(12.dp))
            Card(
                colors = CardDefaults.cardColors(containerColor = BlackCard),
                shape = RoundedCornerShape(16.dp)
            ) {
                AsyncImage(
                    model = "https://i.postimg.cc/R0phZ77L/ESTRELLA-BLANCA.png",
                    contentDescription = "Estrella Stuffies",
                    modifier = Modifier.size(96.dp)
                )
            }
        }
    }
}

@Composable
private fun Bullet(text: String) {
    Row(verticalAlignment = Alignment.Top) {
        Text("• ", color = Color(0xFF111111))
        Text(text, color = Color(0xFF111111))
    }
}

@Composable
private fun BulletBold(label: String, value: String) {
    Row(verticalAlignment = Alignment.Top) {
        Text(
            text = buildAnnotatedString {
                append("• ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold, color = Color.Black)) {
                    append(label)
                }
                append(value)
            },
            color = Color(0xFF111111)
        )
    }
}

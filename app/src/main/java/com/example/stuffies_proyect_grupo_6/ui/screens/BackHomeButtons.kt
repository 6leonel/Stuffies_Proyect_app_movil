package com.example.stuffies_proyect_grupo_6.ui.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.stuffies_proyect_grupo_6.navigation.Route

@Composable
fun BackHomeButtons(navController: NavHostController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Botón BACK
        OutlinedButton(
            onClick = {
                Log.d("StuffiesButtons", "Back clickeado")
                navController.popBackStack()
            },
            shape = RoundedCornerShape(50),
            modifier = Modifier.weight(1f),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color(0xFFB197FC)
            ),
            border = BorderStroke(1.dp, Color(0xFFB197FC))
        ) {
            Text("Back")
        }

        // Botón HOME
        Button(
            onClick = {
                Log.d("StuffiesButtons", "Home clickeado")
                navController.navigate(Route.Home.path) {
                    // volvemos a la pantalla inicial
                    popUpTo(navController.graph.startDestinationId) {
                        inclusive = false
                    }
                    launchSingleTop = true
                }
            },
            shape = RoundedCornerShape(50),
            modifier = Modifier.weight(1f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF7C3AED),
                contentColor = Color.White
            )
        ) {
            Text("Home")
        }
    }
}

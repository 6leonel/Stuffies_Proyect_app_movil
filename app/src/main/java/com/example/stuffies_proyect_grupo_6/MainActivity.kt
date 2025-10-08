package com.example.stuffies_proyect_grupo_6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.stuffies_proyect_grupo_6.navigation.AppNavGraph
import com.example.stuffies_proyect_grupo_6.navigation.Route

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(color = MaterialTheme.colorScheme.background) {
                Root()
            }
        }
    }

    @Composable
    private fun Root() {
        val navController = rememberNavController()
        AppNavGraph(
            navController = navController,
            startDestination = Route.Home.path   // ← sólo Home al iniciar
        )
    }
}

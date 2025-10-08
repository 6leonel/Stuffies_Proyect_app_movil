package com.example.stuffies_proyect_grupo_6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.stuffies_proyect_grupo_6.navigation.AppNavGraph
import com.example.stuffies_proyect_grupo_6.navigation.NavEvent
import com.example.stuffies_proyect_grupo_6.viewmodel.NavViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Puedes envolver con tu tema propio si quieres: StuffiesTheme { ... }
            MaterialTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    val vm: NavViewModel = viewModel()

                    // 🔑 Puente VM -> NavController
                    LaunchedEffect(Unit) {
                        vm.events.collect { ev ->
                            when (ev) {
                                is NavEvent.To -> navController.navigate(ev.route) {
                                    launchSingleTop = true
                                }
                                NavEvent.Back -> navController.popBackStack()
                            }
                        }
                    }

                    AppNavGraph(navController)
                }
            }
        }
    }
}

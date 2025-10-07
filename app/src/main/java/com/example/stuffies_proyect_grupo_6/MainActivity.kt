package com.example.stuffies_proyect_grupo_6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.example.stuffies_proyect_grupo_6.navigation.AppNavGraph
import com.example.stuffies_proyect_grupo_6.ui.theme.Stuffies_Proyect_grupo_6Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Stuffies_Proyect_grupo_6Theme {
                Surface {
                    val navController = rememberNavController()
                    AppNavGraph(navController = navController) // startDestination = Home
                }
            }
        }
    }
}

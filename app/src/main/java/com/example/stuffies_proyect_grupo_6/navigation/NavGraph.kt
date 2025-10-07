package com.example.stuffies_proyect_grupo_6.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.stuffies_proyect_grupo_6.ui.screens.*
import com.example.stuffies_proyect_grupo_6.utils.appWindowSizeClass

@Composable
fun AppNavGraph(navController: NavHostController) {
    val windowSize = appWindowSizeClass()

    NavHost(navController = navController, startDestination = Route.Home.path) {
        composable(Route.Home.path) {
            HomeAdaptive(
                windowSize = windowSize,
                onGoProfile = { navController.navigate(Route.Profile.path) },
                onGoSettings = { navController.navigate(Route.Settings.path) }
            )
        }
        composable(Route.Profile.path) {
            ProfileScreen(onBack = { navController.popBackStack() })
        }
        composable(Route.Settings.path) {
            SettingsScreen(
                onHome = { navController.navigate(Route.Home.path) },
                onProfile = { navController.navigate(Route.Profile.path) }
            )
        }
    }
}

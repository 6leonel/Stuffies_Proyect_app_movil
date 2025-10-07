package com.example.stuffies_proyect_grupo_6.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.stuffies_proyect_grupo_6.ui.screens.*   // ← importa todas tus screens del paquete UNIFICADO
import com.example.stuffies_proyect_grupo_6.viewmodel.UsuarioViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController,
    startDestination: String = Route.Home.path   // arrancamos en tu Home
) {
    val usuarioVM: UsuarioViewModel = viewModel()

    NavHost(navController = navController, startDestination = startDestination) {

        // ---------- HOME REAL (usa tu firma de HomeCompact) ----------
        composable(Route.Home.path) {
            HomeCompact(
                onVerProductos = { /* navega a productos si luego agregas ruta */ },
                onIrContacto   = { /* navega a contacto si luego agregas ruta */ },
                onGoProfile    = { navController.navigate(Route.Profile.path) },
                onGoSettings   = { navController.navigate(Route.Settings.path) }
            )
        }

        // ---------- PANTALLAS EXISTENTES ----------
        composable(Route.Profile.path)  { ProfileScreen(navController) }
        composable(Route.Settings.path) { SettingsScreen(navController) }

        // ---------- GUÍA 11 ----------
        composable(Route.Registro.path) {
            RegistroScreen(
                vm = usuarioVM,
                onContinuar = { navController.navigate(Route.Resumen.path) }
            )
        }
        composable(Route.Resumen.path) {
            ResumenScreen(
                vm = usuarioVM,
                onBack = { navController.popBackStack() },
                onIrHome = {
                    navController.navigate(Route.Home.path) {
                        popUpTo(Route.Registro.path) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

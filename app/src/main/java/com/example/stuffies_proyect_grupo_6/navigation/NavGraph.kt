package com.example.stuffies_proyect_grupo_6.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.stuffies_proyect_grupo_6.ui.screens.HomeScreen
import com.example.stuffies_proyect_grupo_6.ui.screens.ProfileScreen
import com.example.stuffies_proyect_grupo_6.ui.screens.SettingsScreen
import com.example.stuffies_proyect_grupo_6.ui.screens.RegistroScreen
import com.example.stuffies_proyect_grupo_6.ui.screens.ResumenScreen
import com.example.stuffies_proyect_grupo_6.viewmodel.UsuarioViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController,
    startDestination: String = Route.Home.path,
    usuarioVM: UsuarioViewModel? = null   // opcional para Registro/Resumen
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(Route.Home.path) {
            HomeScreen(
                onIrProductos = { /* no-op por ahora */ },
                onIrBlogs = { /* no-op */ },
                onIrNosotros = { /* no-op */ },
                onIrContacto = { /* no-op */ },
                onIrCarrito = { /* no-op */ },
                onIrLogin = { /* no-op */ },
                onIrPerfil = { /* no-op */ }
            )
        }
        // Las siguientes rutas existen para que compile tu NavViewModel,
        // pero no se muestran si no navegas hacia ellas:
        composable(Route.Profile.path) { ProfileScreen(onBack = { navController.popBackStack() }) }
        composable(Route.Settings.path) {
            SettingsScreen(onHome = {
                navController.navigate(Route.Home.path) {
                    popUpTo(Route.Home.path) { inclusive = true }
                    launchSingleTop = true
                }
            })
        }
        if (usuarioVM != null) {
            composable(Route.Registro.path) {
                RegistroScreen(vm = usuarioVM, onContinuar = { navController.navigate(Route.Resumen.path) })
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
}

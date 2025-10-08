package com.example.stuffies_proyect_grupo_6.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.stuffies_proyect_grupo_6.ui.screens.*

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Route.Home.path
    ) {
        // HOME: desde aquí salen todas las rutas
        composable(Route.Home.path) {
            HomeScreen(
                onIrProductos = { navController.navigate(Route.Productos.path) },
                onIrBlogs = { navController.navigate(Route.Blogs.path) },
                onIrNosotros = { navController.navigate(Route.Nosotros.path) },
                onIrContacto = { navController.navigate(Route.Contacto.path) },
                onIrCarrito = { navController.navigate(Route.Carrito.path) },
                onIrLogin = { navController.navigate(Route.Login.path) },
                onIrPerfil = { navController.navigate(Route.Perfil.path) }
            )
        }

        // PANTALLAS PRINCIPALES
        composable(Route.Productos.path) { ProductosScreen() }
        composable(Route.Blogs.path) { BlogsScreen() }
        composable(Route.Nosotros.path) { NosotrosScreen() }
        composable(Route.Contacto.path) { ContactoScreen() }
        composable(Route.Carrito.path) { CarritoScreen() }
        composable(Route.Login.path) { LoginScreen() }
        composable(Route.Perfil.path) { ProfileScreen() }

        // Ya las tienes en /screens (si cambian los nombres, ajusta aquí):
        composable(Route.Registro.path) { RegistroScreen() }
        composable(Route.Resumen.path) { ResumenScreen() }
        composable(Route.Settings.path) { SettingsScreen() }
    }
}

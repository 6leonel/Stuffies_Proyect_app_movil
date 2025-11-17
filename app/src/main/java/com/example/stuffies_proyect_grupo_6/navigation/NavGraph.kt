package com.example.stuffies_proyect_grupo_6.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.stuffies_proyect_grupo_6.ui.screens.AnimacionScreen
import com.example.stuffies_proyect_grupo_6.ui.screens.BlogsScreen
import com.example.stuffies_proyect_grupo_6.ui.screens.CarritoScreen
import com.example.stuffies_proyect_grupo_6.ui.screens.ContactoScreen
import com.example.stuffies_proyect_grupo_6.ui.screens.HomeScreen
import com.example.stuffies_proyect_grupo_6.ui.screens.LoginScreen
import com.example.stuffies_proyect_grupo_6.ui.screens.MapaScreen
import com.example.stuffies_proyect_grupo_6.ui.screens.NosotrosScreen
import com.example.stuffies_proyect_grupo_6.ui.screens.ProductoDetalleScreen
import com.example.stuffies_proyect_grupo_6.ui.screens.ProductosScreen
import com.example.stuffies_proyect_grupo_6.ui.screens.ProfileScreen
import com.example.stuffies_proyect_grupo_6.ui.screens.RegistroScreen
import com.example.stuffies_proyect_grupo_6.ui.screens.ResumenScreen
import com.example.stuffies_proyect_grupo_6.ui.screens.SettingsScreen
import com.example.stuffies_proyect_grupo_6.viewmodel.UsuarioViewModel

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Route.Home.path
    ) {
        // ===================== HOME =====================
        composable(Route.Home.path) {
            HomeScreen(
                onIrProductos = { navController.navigate(Route.Productos.path) },
                onIrBlogs     = { navController.navigate(Route.Blogs.path) },
                onIrNosotros  = { navController.navigate(Route.Nosotros.path) },
                onIrAnimacion = { navController.navigate(Route.Animacion.path) },
                onIrContacto  = { navController.navigate(Route.Contacto.path) },
                onIrCarrito   = { navController.navigate(Route.Carrito.path) },
                onIrLogin     = { navController.navigate(Route.Login.path) },
                onIrPerfil    = { navController.navigate(Route.Perfil.path) },
                onIrRegistro  = { navController.navigate(Route.Registro.path) },
                onIrMapa      = { navController.navigate(Route.Mapa.path) }
            )
        }

        // ========== PANTALLAS PRINCIPALES ==========
        composable(Route.Productos.path) {
            ProductosScreen(
                onVerDetalle = { id ->
                    navController.navigate(Route.ProductoDetalle.create(id))
                },
                onAgregar = { _ ->
                    // lógica opcional al agregar al carrito
                }
            )
        }

        composable(Route.Blogs.path)     { BlogsScreen() }
        composable(Route.Nosotros.path)  { NosotrosScreen() }
        composable(Route.Animacion.path) { AnimacionScreen() }
        composable(Route.Contacto.path)  { ContactoScreen() }
        composable(Route.Carrito.path)   { CarritoScreen() }
        composable(Route.Login.path)     { LoginScreen() }
        composable(Route.Perfil.path)    { ProfileScreen() }

        // ========== MAPA ==========
        composable(Route.Mapa.path) {
            MapaScreen(
                onBack = { navController.popBackStack() }
            )
        }

        // ========== SETTINGS ==========
        composable(Route.Settings.path) {
            SettingsScreen()
        }

        // ========== SUB-GRAPH REGISTRO ==========
        navigation(
            route = "registro_graph",
            startDestination = Route.Registro.path
        ) {
            // Pantalla de registro
            composable(Route.Registro.path) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("registro_graph")
                }
                val vm: UsuarioViewModel = viewModel(parentEntry)

                RegistroScreen(
                    navController = navController,
                    viewModel     = vm
                )
            }

            // Pantalla de resumen (con botones Back / Home)
            composable(Route.Resumen.path) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("registro_graph")
                }
                val vm: UsuarioViewModel = viewModel(parentEntry)

                ResumenScreen(
                    navController = navController,
                    vm = vm
                )
            }
        }

        // ========== DETALLE PRODUCTO CON ARGUMENTO ==========
        composable(
            route = Route.ProductoDetalle.path,
            arguments = listOf(
                navArgument("id") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            ProductoDetalleScreen(id = id)
        }
    }
}

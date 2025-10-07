package com.example.stuffies_proyect_grupo_6.navigation

sealed interface NavEvent {
    data class To(val route: String) : NavEvent
    data object Back : NavEvent
}

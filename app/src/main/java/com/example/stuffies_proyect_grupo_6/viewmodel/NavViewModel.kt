package com.example.stuffies_proyect_grupo_6.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stuffies_proyect_grupo_6.navigation.NavEvent
import com.example.stuffies_proyect_grupo_6.navigation.Route
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class NavViewModel : ViewModel() {
    private val _events = MutableSharedFlow<NavEvent>()
    val events = _events.asSharedFlow()

    fun goHome()     = send(NavEvent.To(Route.Home.path))
    fun goProfile()  = send(NavEvent.To(Route.Perfil.path))   // <-- aquí estaba el error (Perfil)
    fun goSettings() = send(NavEvent.To(Route.Settings.path))
    fun back()       = send(NavEvent.Back)

    private fun send(event: NavEvent) = viewModelScope.launch { _events.emit(event) }
}

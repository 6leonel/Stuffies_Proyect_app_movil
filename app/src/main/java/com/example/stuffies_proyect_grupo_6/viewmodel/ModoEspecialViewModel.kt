package com.example.stuffies_proyect_grupo_6.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.stuffies_proyect_grupo_6.data.SettingsRepository
import com.example.stuffies_proyect_grupo_6.data.settingsDataStore
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ModoEspecialViewModel(private val repo: SettingsRepository) : ViewModel() {

    private val _loading = MutableStateFlow(true)
    val loading: StateFlow<Boolean> = _loading

    private val _enabled = MutableStateFlow(false)
    val enabled: StateFlow<Boolean> = _enabled

    private val _savedOnce = MutableStateFlow(false) // para feedback de éxito
    val savedOnce: StateFlow<Boolean> = _savedOnce

    init {
        // Simular carga inicial (loader antes de mostrar la UI principal)
        viewModelScope.launch {
            _enabled.value = repo.specialMode.first()
            delay(600) // loader breve
            _loading.value = false
        }
    }

    fun toggleLocal() { _enabled.value = !_enabled.value }

    fun save() {
        viewModelScope.launch {
            repo.setSpecialMode(_enabled.value)
            _savedOnce.value = true
            delay(1500)
            _savedOnce.value = false
        }
    }

    companion object {
        fun factory(context: Context): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    val repo = SettingsRepository(context.settingsDataStore)
                    @Suppress("UNCHECKED_CAST")
                    return ModoEspecialViewModel(repo) as T
                }
            }
    }
}

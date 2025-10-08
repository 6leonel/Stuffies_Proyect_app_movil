package com.example.stuffies_proyect_grupo_6.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Extension del Context para obtener el DataStore
val Context.settingsDataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsRepository(private val dataStore: DataStore<Preferences>) {
    private object Keys {
        val SPECIAL_MODE = booleanPreferencesKey("special_mode")
    }

    val specialMode: Flow<Boolean> =
        dataStore.data.map { it[Keys.SPECIAL_MODE] ?: false }

    suspend fun setSpecialMode(enabled: Boolean) {
        dataStore.edit { it[Keys.SPECIAL_MODE] = enabled }
    }
}

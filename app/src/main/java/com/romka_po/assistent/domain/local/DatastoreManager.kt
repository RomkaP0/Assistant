package com.romka_po.assistent.domain.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.romka_po.assistent.model.theme.TypeTheme
import com.romka_po.assistent.ui.theme.Color
import kotlinx.coroutines.flow.map


class DatastoreManager(
    context: Context
) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_settings")

    private val datastore = context.dataStore

    val themeSettingsFlow = datastore.data.map {
        TypeTheme.valueOf(it[stringPreferencesKey("theme")]?: TypeTheme.AUTO.name)
    }

    val colorSettingsFlow = datastore.data.map {
        it[intPreferencesKey("color")] ?: Color.BROWN.ordinal
    }

    suspend fun changeTheme(typeTheme:TypeTheme) {
        datastore.edit { preferences ->
            preferences[stringPreferencesKey("theme")] = typeTheme.name
        }
    }

    suspend fun changeColor(color: Int) {
        datastore.edit { preferences ->
            preferences[intPreferencesKey("color")] = color
        }
    }

    val isFirstOpen = datastore.data.map {
        it[booleanPreferencesKey("is_first_open")]?:true
    }
}
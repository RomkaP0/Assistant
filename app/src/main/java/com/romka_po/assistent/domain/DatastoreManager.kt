package com.romka_po.assistent.domain

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.romka_po.assistent.model.theme.TypeTheme
import kotlinx.coroutines.flow.map


class DatastoreManager(
    context: Context
) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_settings")

    private val datastore = context.dataStore

    val themeSettingsFlow = datastore.data.map {
        TypeTheme.valueOf(it[stringPreferencesKey("theme")]?: TypeTheme.AUTO.name)
    }

    suspend fun changeTheme(typeTheme:TypeTheme) {
        datastore.edit { preferences ->
            preferences[stringPreferencesKey("theme")] = typeTheme.name
        }
    }




}
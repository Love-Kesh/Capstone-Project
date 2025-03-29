package com.example.capstoneproject

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Extension property to create a DataStore<Preferences>
val Context.settingsDataStore: DataStore<Preferences> by preferencesDataStore(name = "settings_preferences")

// Keys for each toggle
object SettingsKeys {
    val GHOST_MODE = booleanPreferencesKey("ghost_mode")
    val ANONYMOUS_MESSAGES = booleanPreferencesKey("anonymous_messages")
    val HIATUS_MODE = booleanPreferencesKey("hiatus_mode")
}

// Functions to update toggles
suspend fun setGhostMode(context: Context, enabled: Boolean) {
    context.settingsDataStore.edit { prefs ->
        prefs[SettingsKeys.GHOST_MODE] = enabled
    }
}

suspend fun setAnonymousMessages(context: Context, enabled: Boolean) {
    context.settingsDataStore.edit { prefs ->
        prefs[SettingsKeys.ANONYMOUS_MESSAGES] = enabled
    }
}

suspend fun setHiatusMode(context: Context, enabled: Boolean) {
    context.settingsDataStore.edit { prefs ->
        prefs[SettingsKeys.HIATUS_MODE] = enabled
    }
}

// Flows to read toggles
fun getGhostModeFlow(context: Context): Flow<Boolean> =
    context.settingsDataStore.data.map { prefs ->
        prefs[SettingsKeys.GHOST_MODE] ?: false
    }

fun getAnonymousMessagesFlow(context: Context): Flow<Boolean> =
    context.settingsDataStore.data.map { prefs ->
        prefs[SettingsKeys.ANONYMOUS_MESSAGES] ?: false
    }

fun getHiatusModeFlow(context: Context): Flow<Boolean> =
    context.settingsDataStore.data.map { prefs ->
        prefs[SettingsKeys.HIATUS_MODE] ?: false
    }

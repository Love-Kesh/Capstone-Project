package com.example.capstoneproject

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class SettingsState(
    val ghostMode: Boolean = false,
    val anonymousMessages: Boolean = false,
    val hiatusMode: Boolean = false
)

class SettingsViewModel(application: Application) : AndroidViewModel(application) {

    private val context = application.applicationContext

    // Observe the three toggle flows from DataStore
    private val ghostFlow = getGhostModeFlow(context)
    private val anonymousFlow = getAnonymousMessagesFlow(context)
    private val hiatusFlow = getHiatusModeFlow(context)

    // Combine them into a single StateFlow for the UI
    val uiState: StateFlow<SettingsState> = combine(
        ghostFlow,
        anonymousFlow,
        hiatusFlow
    ) { ghost, anonymous, hiatus ->
        SettingsState(ghost, anonymous, hiatus)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = SettingsState()
    )

    // Update toggles
    fun updateGhostMode(enabled: Boolean) {
        viewModelScope.launch {
            setGhostMode(context, enabled)
        }
    }

    fun updateAnonymousMessages(enabled: Boolean) {
        viewModelScope.launch {
            setAnonymousMessages(context, enabled)
        }
    }

    fun updateHiatusMode(enabled: Boolean) {
        viewModelScope.launch {
            setHiatusMode(context, enabled)
        }
    }
}

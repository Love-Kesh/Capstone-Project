@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.capstoneproject

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onNavigateBack: () -> Unit,
    vm: SettingsViewModel = viewModel()
) {
    val state = vm.uiState.collectAsState()

    // Create a subtle vertical gradient background
    val backgroundBrush = Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
            MaterialTheme.colorScheme.background
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings & Extra Features") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            painter = painterResource(id = android.R.drawable.ic_media_previous),
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .background(brush = backgroundBrush)
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Toggle your extra features below:",
                style = MaterialTheme.typography.titleMedium
            )

            // Wrap the toggles in a Card for a neat elevated look
            Card(
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    RowSwitch(
                        label = "Ghost Mode",
                        checked = state.value.ghostMode,
                        onCheckedChange = { vm.updateGhostMode(it) }
                    )
                    Divider(modifier = Modifier.padding(vertical = 8.dp))
                    RowSwitch(
                        label = "Anonymous Messages",
                        checked = state.value.anonymousMessages,
                        onCheckedChange = { vm.updateAnonymousMessages(it) }
                    )
                    Divider(modifier = Modifier.padding(vertical = 8.dp))
                    RowSwitch(
                        label = "Hiatus Mode",
                        checked = state.value.hiatusMode,
                        onCheckedChange = { vm.updateHiatusMode(it) }
                    )
                }
            }
        }
    }
}

@Composable
fun RowSwitch(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, style = MaterialTheme.typography.bodyLarge)
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedTrackColor = MaterialTheme.colorScheme.primary
            )
        )
    }
}

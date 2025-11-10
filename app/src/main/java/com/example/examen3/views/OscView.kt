package com.example.examen3.views

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import com.example.examen3.dataStore
import com.example.examen3.components.MyTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OscView(navController: NavController) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val darkModeKey = booleanPreferencesKey("dark_mode")

    val isDarkModeFlow = context.dataStore.data.map { prefs ->
        prefs[darkModeKey] ?: false
    }
    val isDarkMode by isDarkModeFlow.collectAsState(initial = false)

    Scaffold(
        topBar = {
            MyTopAppBar(
                title = "Cambiar Tema",
                showBackButton = true,
                onBackClick = { navController.popBackStack() }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(24.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Crossfade(targetState = isDarkMode, label = "themeText", animationSpec = tween(500)) { isDark ->
                Text(
                    text = if (isDark) "Tema actual: Oscuro" else "Tema actual: Claro",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
            Switch(
                checked = isDarkMode,
                onCheckedChange = {
                    scope.launch {
                        context.dataStore.edit { prefs ->
                            prefs[darkModeKey] = it
                        }
                    }
                },
                thumbContent = {
                    Icon(
                        imageVector = if (isDarkMode) Icons.Filled.DarkMode else Icons.Filled.LightMode,
                        contentDescription = "Icono de tema",
                        modifier = Modifier.size(SwitchDefaults.IconSize),
                    )
                }
            )
        }
    }
}

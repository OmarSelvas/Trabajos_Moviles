package com.example.examen3.views

import androidx.compose.foundation.layout.*
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
            Text(
                text = if (isDarkMode) "Tema actual: Oscuro" else "Tema actual: Claro",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    scope.launch {
                        context.dataStore.edit { prefs ->
                            prefs[darkModeKey] = !isDarkMode
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(0.8f) // Opcional: ajusta el ancho como en HomeView
            ) {
                Text(if (isDarkMode) "Cambiar a Claro" else "Cambiar a Oscuro")
            }

        }
    }
}

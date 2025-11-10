package com.example.examen3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.datastore.preferences.core.booleanPreferencesKey
import com.example.examen3.navigation.NavManager
import kotlinx.coroutines.flow.map


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = this
            val darkModeKey = booleanPreferencesKey("dark_mode")

            val isDarkModeFlow = context.dataStore.data.map { prefs ->
                prefs[darkModeKey] ?: false
            }

            val isDarkMode by isDarkModeFlow.collectAsState(initial = false)

            AnimatedAppTheme(isDarkMode = isDarkMode) {
                NavManager()
            }
        }
    }
}

@Composable
fun AnimatedAppTheme(
    isDarkMode: Boolean,
    content: @Composable () -> Unit
) {
    val verdeAqua = Color(0xFF00A99D)
    val blanco = Color.White

    val lightColors = lightColorScheme(
        primary = verdeAqua,
        onPrimary = blanco
    )
    val darkColors = darkColorScheme(
        primary = verdeAqua,
        onPrimary = blanco
    )

    val targetColorScheme = if (isDarkMode) darkColors else lightColors

    val primaryColor by animateColorAsState(targetValue = targetColorScheme.primary, animationSpec = tween(600))
    val onPrimaryColor by animateColorAsState(targetValue = targetColorScheme.onPrimary, animationSpec = tween(600))
    val secondaryColor by animateColorAsState(targetValue = targetColorScheme.secondary, animationSpec = tween(600))
    val onSecondaryColor by animateColorAsState(targetValue = targetColorScheme.onSecondary, animationSpec = tween(600))
    val backgroundColor by animateColorAsState(targetValue = targetColorScheme.background, animationSpec = tween(600))
    val surfaceColor by animateColorAsState(targetValue = targetColorScheme.surface, animationSpec = tween(600))
    val onBackgroundColor by animateColorAsState(targetValue = targetColorScheme.onBackground, animationSpec = tween(600))
    val onSurfaceColor by animateColorAsState(targetValue = targetColorScheme.onSurface, animationSpec = tween(600))

    val animatedColorScheme = targetColorScheme.copy(
        primary = primaryColor,
        onPrimary = onPrimaryColor,
        secondary = secondaryColor,
        onSecondary = onSecondaryColor,
        background = backgroundColor,
        surface = surfaceColor,
        onBackground = onBackgroundColor,
        onSurface = onSurfaceColor
    )

    MaterialTheme(
        colorScheme = animatedColorScheme
    ) {
        content()
    }
}


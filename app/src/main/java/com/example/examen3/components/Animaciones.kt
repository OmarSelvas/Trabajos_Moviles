package com.example.examen3.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer


fun Modifier.pressClickEffect(): Modifier = composed {
    val interactionSource = remember { MutableInteractionSource() }

    // Observa si el Composable está siendo presionado
    val isPressed by interactionSource.collectIsPressedAsState()

    val scale by animateFloatAsState(targetValue = if (isPressed) 0.95f else 1f)

    this.graphicsLayer {
        scaleX = scale
        scaleY = scale
    }
}

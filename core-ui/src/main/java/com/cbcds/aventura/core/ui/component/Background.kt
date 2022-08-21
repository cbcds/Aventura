package com.cbcds.aventura.core.ui.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun Background(
    modifier: Modifier = Modifier,
    color: Color? = null,
    content: @Composable () -> Unit
) {
    Surface(
        color = color ?: MaterialTheme.colorScheme.surface,
        modifier = modifier.fillMaxSize()
    ) {
        content()
    }
}
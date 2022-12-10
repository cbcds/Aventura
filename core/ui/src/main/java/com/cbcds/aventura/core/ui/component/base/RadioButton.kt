package com.cbcds.aventura.core.ui.component.base

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun RadioButton(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: RadioButtonColors = RadioButtonDefaults.radioButtonColors()
) {
    androidx.compose.material3.RadioButton(
        selected = selected,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = colors
    )
}

object RadioButtonDefaults {

    private const val DisabledRadioButtonAlpha = 0.35f

    @Composable
    fun radioButtonColors(
        selectedColor: Color = MaterialTheme.colorScheme.primary,
        unselectedColor: Color = MaterialTheme.colorScheme.outline,
        disabledSelectedColor: Color = selectedColor.copy(alpha = DisabledRadioButtonAlpha),
        disabledUnselectedColor: Color = unselectedColor.copy(alpha = DisabledRadioButtonAlpha)
    ) = androidx.compose.material3.RadioButtonDefaults.colors(
        selectedColor = selectedColor,
        unselectedColor = unselectedColor,
        disabledSelectedColor = disabledSelectedColor,
        disabledUnselectedColor = disabledUnselectedColor
    )
}
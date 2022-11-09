package com.cbcds.aventura.core.ui.component

import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

// TODO: change corner radius
@Composable
fun Checkbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: CheckboxColors = CheckboxDefaults.checkboxColors()
) {
    androidx.compose.material3.Checkbox(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier,
        enabled = enabled,
        colors = colors
    )
}

object CheckboxDefaults {

    private const val DisabledCheckboxAlpha = 0.35f

    @Composable
    fun checkboxColors(
        checkedColor: Color = MaterialTheme.colorScheme.primary,
        uncheckedColor: Color = MaterialTheme.colorScheme.outline,
        checkmarkColor: Color = MaterialTheme.colorScheme.background,
        disabledCheckedColor: Color = checkedColor.copy(alpha = DisabledCheckboxAlpha),
        disabledUncheckedColor: Color = uncheckedColor.copy(alpha = DisabledCheckboxAlpha)
    ) = androidx.compose.material3.CheckboxDefaults.colors(
        checkedColor = checkedColor,
        uncheckedColor = uncheckedColor,
        checkmarkColor = checkmarkColor,
        disabledCheckedColor = disabledCheckedColor,
        disabledUncheckedColor = disabledUncheckedColor,
    )
}
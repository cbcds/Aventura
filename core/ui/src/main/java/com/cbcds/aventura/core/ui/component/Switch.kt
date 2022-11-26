package com.cbcds.aventura.core.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwitchColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun Switch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: SwitchColors = SwitchDefaults.switchColors()
) {
    androidx.compose.material3.Switch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier,
        thumbContent = { Icon(imageVector = Icons.Default.Add, contentDescription = "") },
        enabled = enabled,
        colors = colors
    )
}

object SwitchDefaults {

    @Composable
    fun switchColors(
        checkedThumbColor: Color = MaterialTheme.colorScheme.onPrimary,
        checkedTrackColor: Color = MaterialTheme.colorScheme.primary,
        uncheckedThumbColor: Color = MaterialTheme.colorScheme.onPrimary,
        uncheckedTrackColor: Color = MaterialTheme.colorScheme.surfaceVariant,
        disabledCheckedThumbColor: Color = checkedThumbColor.copy(
            alpha = disabledSwitchAlpha(true)
        ),
        disabledCheckedTrackColor: Color = checkedTrackColor.copy(
            alpha = disabledSwitchAlpha(true)
        ),
        disabledUncheckedThumbColor: Color = uncheckedThumbColor.copy(
            alpha = disabledSwitchAlpha(false)
        ),
        disabledUncheckedTrackColor: Color = uncheckedTrackColor.copy(
            alpha = disabledSwitchAlpha(false)
        ),
    ) = androidx.compose.material3.SwitchDefaults.colors(
        checkedThumbColor = checkedThumbColor,
        checkedTrackColor = checkedTrackColor,
        checkedBorderColor = Color.Transparent,
        checkedIconColor = Color.Transparent,
        uncheckedThumbColor = uncheckedThumbColor,
        uncheckedTrackColor = uncheckedTrackColor,
        uncheckedBorderColor = Color.Transparent,
        uncheckedIconColor = Color.Transparent,
        disabledCheckedThumbColor = disabledCheckedThumbColor,
        disabledCheckedTrackColor = disabledCheckedTrackColor,
        disabledCheckedBorderColor = Color.Transparent,
        disabledCheckedIconColor = Color.Transparent,
        disabledUncheckedThumbColor = disabledUncheckedThumbColor,
        disabledUncheckedTrackColor = disabledUncheckedTrackColor,
        disabledUncheckedBorderColor = Color.Transparent,
        disabledUncheckedIconColor = Color.Transparent
    )

    @Composable
    private fun disabledSwitchAlpha(checked: Boolean) = if (checked) 0.3f else 0.8f
}
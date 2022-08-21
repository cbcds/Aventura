package com.cbcds.aventura.core.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun FilledButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = ButtonDefaults.ButtonShape,
    colors: ButtonColors = ButtonDefaults.filledButtonColors(),
    contentPadding: PaddingValues = ButtonDefaults.PaddingValues,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        colors = colors,
        contentPadding = contentPadding,
        content = content
    )
}

@Composable
fun FilledTextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = ButtonDefaults.ButtonShape,
    colors: ButtonColors = ButtonDefaults.filledButtonColors(),
    contentPadding: PaddingValues = ButtonDefaults.PaddingValues,
    content: @Composable RowScope.() -> Unit
) {
    FilledButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        colors = colors,
        contentPadding = contentPadding,
        content = {
            ProvideTextStyle(value = MaterialTheme.typography.titleSmall) {
                content()
            }
        }
    )
}

@Composable
fun FilledIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = ButtonDefaults.ButtonShape,
    colors: ButtonColors = ButtonDefaults.filledButtonColors(),
    contentPadding: PaddingValues = ButtonDefaults.IconPaddingValues,
    content: @Composable RowScope.() -> Unit
) {
    FilledButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        colors = colors,
        contentPadding = contentPadding,
        content = content
    )
}

@Composable
fun IconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: ButtonColors = ButtonDefaults.iconButtonColors(),
    contentPadding: PaddingValues = ButtonDefaults.IconPaddingValues,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = colors,
        contentPadding = contentPadding,
        content = content
    )
}

// TODO: uncomment when this component is fully supported
/*@Composable
fun FilledIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: IconButtonColors = ButtonDefaults.filledIconButtonColors(),
    content: @Composable () -> Unit
) {
    androidx.compose.material3.FilledIconButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = colors,
        content = content
    )
}*/

@Composable
fun OutlinedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = ButtonDefaults.ButtonShape,
    border: BorderStroke = ButtonDefaults.outlinedButtonBorder(enabled = enabled),
    colors: ButtonColors = ButtonDefaults.outlinedButtonColors(),
    contentPadding: PaddingValues = ButtonDefaults.PaddingValues,
    content: @Composable RowScope.() -> Unit
) {
    androidx.compose.material3.OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        border = border,
        colors = colors,
        contentPadding = contentPadding,
        content = content
    )
}

@Composable
fun OutlinedTextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = ButtonDefaults.ButtonShape,
    border: BorderStroke = ButtonDefaults.outlinedButtonBorder(enabled = enabled),
    colors: ButtonColors = ButtonDefaults.outlinedButtonColors(),
    contentPadding: PaddingValues = ButtonDefaults.PaddingValues,
    content: @Composable RowScope.() -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        border = border,
        colors = colors,
        contentPadding = contentPadding,
        content = {
            ProvideTextStyle(value = MaterialTheme.typography.titleSmall) {
                content()
            }
        }
    )
}

object ButtonDefaults {

    private val ButtonVerticalPadding = 12.dp
    private val ButtonHorizontalPadding = 20.dp
    private val IconButtonPadding = 12.dp

    val PaddingValues =
        PaddingValues(horizontal = ButtonHorizontalPadding, vertical = ButtonVerticalPadding)
    val IconPaddingValues = PaddingValues(IconButtonPadding)

    private const val DisabledButtonContainerAlpha = 0.35f

    @Composable
    private fun disabledFilledButtonContentAlpha() = if (isSystemInDarkTheme()) 0.9f else 0.5f

    val ButtonShape = RoundedCornerShape(14.dp)

    @Composable
    fun outlinedButtonBorder(
        enabled: Boolean,
        width: Dp = 2.dp,
        color: Color = MaterialTheme.colorScheme.primary,
        disabledColor: Color = color.copy(alpha = DisabledButtonContainerAlpha)
    ): BorderStroke = BorderStroke(
        width = width,
        color = if (enabled) color else disabledColor
    )

    @Composable
    fun filledButtonColors(
        containerColor: Color = MaterialTheme.colorScheme.primary,
        contentColor: Color = MaterialTheme.colorScheme.onPrimary,
        disabledContainerColor: Color = containerColor.copy(alpha = DisabledButtonContainerAlpha),
        disabledContentColor: Color = contentColor.copy(alpha = disabledFilledButtonContentAlpha())
    ) = androidx.compose.material3.ButtonDefaults.buttonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor
    )

    @Composable
    fun outlinedButtonColors(
        containerColor: Color = Color.Transparent,
        contentColor: Color = MaterialTheme.colorScheme.primary,
        disabledContainerColor: Color = Color.Transparent,
        disabledContentColor: Color = contentColor.copy(alpha = DisabledButtonContainerAlpha)
    ) = androidx.compose.material3.ButtonDefaults.outlinedButtonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor
    )

    @Composable
    fun iconButtonColors(
        containerColor: Color = Color.Transparent,
        contentColor: Color = MaterialTheme.colorScheme.onBackground,
        disabledContainerColor: Color = Color.Transparent,
        disabledContentColor: Color = MaterialTheme.colorScheme.primary.copy(
            alpha = DisabledButtonContainerAlpha
        )
    ) = androidx.compose.material3.ButtonDefaults.buttonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor
    )
}

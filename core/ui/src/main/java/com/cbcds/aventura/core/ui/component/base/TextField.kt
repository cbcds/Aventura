package com.cbcds.aventura.core.ui.component.base

import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.cbcds.aventura.core.ui.component.base.TextFieldDefaults.textFieldHelperTextPaddingValues
import com.cbcds.aventura.core.ui.component.base.TextFieldDefaults.textFieldLabelPaddingValues
import com.cbcds.aventura.core.ui.component.base.TextFieldDefaults.textFieldTextStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    label: String? = null,
    hint: String? = null,
    helperText: String? = null,
    trailingIcon: @Composable() (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = TextFieldDefaults.TextFieldShape,
    colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors()
) {
    Column(modifier = modifier) {
        if (label != null) {
            Text(
                text = label,
                style = TextFieldDefaults.textFieldLabelTextStyle(),
                color = TextFieldDefaults.textFieldLabelColor(),
                modifier = Modifier.padding(textFieldLabelPaddingValues)
            )
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            androidx.compose.material3.OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = value,
                onValueChange = onValueChange,
                textStyle = textFieldTextStyle(),
                enabled = enabled,
                readOnly = readOnly,
                placeholder = hint?.let { { Text(text = hint) } },
                trailingIcon = trailingIcon,
                isError = isError,
                visualTransformation = visualTransformation,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                singleLine = singleLine,
                maxLines = maxLines,
                interactionSource = interactionSource,
                shape = shape,
                colors = colors,
            )
        }

        Text(
            text = helperText.orEmpty(),
            style = TextFieldDefaults.textFieldHelperTextStyle(),
            color = TextFieldDefaults.textFieldHelperTextColor(
                colors = colors,
                enabled = enabled,
                isError = isError,
                interactionSource = interactionSource
            ),
            modifier = Modifier.padding(textFieldHelperTextPaddingValues)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
object TextFieldDefaults {

    val TextFieldShape = RoundedCornerShape(14.dp)

    val textFieldLabelPaddingValues = PaddingValues(start = 2.dp, bottom = 8.dp)
    val textFieldHelperTextPaddingValues = PaddingValues(start = 2.dp, top = 4.dp)

    @Composable
    fun textFieldTextStyle() = MaterialTheme.typography.bodyLarge

    @Composable
    fun textFieldLabelTextStyle() = MaterialTheme.typography.bodyMedium

    @Composable
    fun textFieldHelperTextStyle() = MaterialTheme.typography.labelSmall

    @Composable
    fun outlinedTextFieldColors(
        textColor: Color = MaterialTheme.colorScheme.onSurface,
        disabledTextColor: Color = MaterialTheme.colorScheme.onSurface
            .copy(alpha = DisabledTextFieldAlpha),
        containerColor: Color = MaterialTheme.colorScheme.surfaceVariant
            .copy(alpha = TextFieldContainerAlpha),
        focusedBorderColor: Color = MaterialTheme.colorScheme.primary,
        unfocusedBorderColor: Color = MaterialTheme.colorScheme.outline
            .copy(alpha = TextFieldBorderAlpha),
        disabledBorderColor: Color = MaterialTheme.colorScheme.onSurface
            .copy(alpha = DisabledTextFieldAlpha),
        errorBorderColor: Color = MaterialTheme.colorScheme.error,
        unfocusedLabelColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
        disabledLabelColor: Color = MaterialTheme.colorScheme.onSurface
            .copy(alpha = DisabledTextFieldAlpha),
        errorLabelColor: Color = MaterialTheme.colorScheme.error,
        placeholderColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
        disabledPlaceholderColor: Color = MaterialTheme.colorScheme.onSurfaceVariant
            .copy(alpha = DisabledTextFieldAlpha),
    ): TextFieldColors = androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors(
        textColor = textColor,
        disabledTextColor = disabledTextColor,
        containerColor = containerColor,
        focusedBorderColor = focusedBorderColor,
        unfocusedBorderColor = unfocusedBorderColor,
        disabledBorderColor = disabledBorderColor,
        errorBorderColor = errorBorderColor,
        unfocusedLabelColor = unfocusedLabelColor,
        disabledLabelColor = disabledLabelColor,
        errorLabelColor = errorLabelColor,
        placeholderColor = placeholderColor,
        disabledPlaceholderColor = disabledPlaceholderColor
    )

    @Composable
    fun textFieldLabelColor() =
        MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = TextFieldTitleAlpha)


    @Composable
    fun textFieldHelperTextColor(
        colors: TextFieldColors,
        enabled: Boolean,
        isError: Boolean,
        interactionSource: InteractionSource
    ) = colors.labelColor(
        enabled = enabled,
        isError = isError,
        interactionSource = interactionSource
    ).value

    private const val DisabledTextFieldAlpha = 0.35f
    private const val TextFieldTitleAlpha = 0.8f
    private const val TextFieldBorderAlpha = 0.3f
    private const val TextFieldContainerAlpha = 0.4f
}

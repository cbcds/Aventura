package com.cbcds.aventura.feature.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.cbcds.aventura.core.ui.component.base.IconButton
import com.cbcds.aventura.core.ui.component.base.OutlinedTextField
import com.cbcds.aventura.core.ui.component.base.TextFieldDefaults
import com.cbcds.aventura.core.ui.utils.toKeyboardActions
import com.cbcds.aventura.core.ui.R as coreR

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun UsernameTextField(
    username: String,
    onUsernameChange: (String) -> Unit,
    error: String? = null,
    imeAction: ImeAction,
    onImeAction: (KeyboardActionScope.() -> Unit)? = null,
    modifier: Modifier,
) {
    OutlinedTextField(
        value = username,
        onValueChange = onUsernameChange,
        label = stringResource(R.string.username_text_field_title),
        helperText = error,
        isError = error != null,
        keyboardOptions = KeyboardOptions(imeAction = imeAction),
        keyboardActions = onImeAction.toKeyboardActions(),
        maxLines = 1,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun EmailTextField(
    email: String,
    onEmailChange: (String) -> Unit,
    error: String? = null,
    imeAction: ImeAction,
    onImeAction: (KeyboardActionScope.() -> Unit)? = null,
    modifier: Modifier
) {
    OutlinedTextField(
        value = email,
        onValueChange = onEmailChange,
        label = stringResource(R.string.email_text_field_title),
        helperText = error,
        isError = error != null,
        keyboardOptions = KeyboardOptions(imeAction = imeAction),
        keyboardActions = onImeAction.toKeyboardActions(),
        maxLines = 1,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PasswordTextField(
    password: String,
    onPasswordChange: (String) -> Unit,
    error: String? = null,
    imeAction: ImeAction,
    onImeAction: (KeyboardActionScope.() -> Unit)? = null,
    modifier: Modifier
) {
    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }
    val visibilityIconId =
        if (isPasswordVisible) coreR.drawable.ic_visibility_on else coreR.drawable.ic_visibility_off
    val passwordVisibilityButton = @Composable {
        IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
            Image(
                painter = painterResource(visibilityIconId),
                colorFilter = ColorFilter.tint(
                    TextFieldDefaults.outlinedTextFieldColors().textColor(enabled = true).value
                ),
                contentDescription = null,
            )
        }
    }
    val visualTransformation =
        if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()

    OutlinedTextField(
        value = password,
        onValueChange = onPasswordChange,
        label = stringResource(R.string.password_text_field_title),
        helperText = error,
        trailingIcon = passwordVisibilityButton,
        visualTransformation = visualTransformation,
        isError = error != null,
        keyboardOptions = KeyboardOptions(imeAction = imeAction),
        keyboardActions = onImeAction.toKeyboardActions(),
        maxLines = 1,
        modifier = modifier,
    )
}

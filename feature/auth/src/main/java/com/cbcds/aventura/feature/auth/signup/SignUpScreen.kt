package com.cbcds.aventura.feature.auth.signup

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cbcds.aventura.core.common.exception.EmailAlreadyInUseException
import com.cbcds.aventura.core.ui.component.AppLogo
import com.cbcds.aventura.core.ui.component.base.FilledTextButton
import com.cbcds.aventura.core.ui.component.base.IconButton
import com.cbcds.aventura.core.ui.component.base.TextButton
import com.cbcds.aventura.core.ui.screen.LoadingScreen
import com.cbcds.aventura.core.ui.utils.withClearFocus
import com.cbcds.aventura.feature.auth.EmailTextField
import com.cbcds.aventura.feature.auth.PasswordTextField
import com.cbcds.aventura.feature.auth.R
import com.cbcds.aventura.feature.auth.UsernameTextField
import com.cbcds.aventura.feature.auth.utils.toErrorStringId

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val signUpState by viewModel.signUpUiState.collectAsStateWithLifecycle()

    var username by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    val focusManager = LocalFocusManager.current

    SignUpScreen(
        signUpState = signUpState,
        username = username,
        onUsernameChange = { username = it },
        email = email,
        onEmailChange = { email = it },
        password = password,
        onPasswordChange = { password = it },
        onBlankSpaceClick = { focusManager.clearFocus() },
        onBackClick = viewModel::onBackClick,
        onSignUpClick =
        { viewModel.signUp(username, email, password) }.withClearFocus(focusManager),
        onGoogleAuthClick = viewModel::authWithGoogle.withClearFocus(focusManager),
        onFacebookAuthClick = viewModel::authWithFacebook.withClearFocus(focusManager),
        onGithubAuthClick = viewModel::authWithGithub.withClearFocus(focusManager),
        onSignInClick = viewModel::toSignInScreen,
    )
}

@Composable
private fun SignUpScreen(
    signUpState: SignUpUiState,
    username: String,
    onUsernameChange: (String) -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    onBlankSpaceClick: (Offset) -> Unit,
    onBackClick: () -> Unit,
    onSignUpClick: () -> Unit,
    onGoogleAuthClick: () -> Unit,
    onFacebookAuthClick: () -> Unit,
    onGithubAuthClick: () -> Unit,
    onSignInClick: () -> Unit,
) {
    val isLoadingState = signUpState is SignUpUiState.Initial && signUpState.showLoading ||
            signUpState is SignUpUiState.ValidationError && signUpState.showLoading

    BackHandler(enabled = isLoadingState) {
        onBackClick()
    }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = scrollState)
            .pointerInput(Unit) {
                detectTapGestures(onTap = onBlankSpaceClick)
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppLogo(
            modifier = Modifier.padding(top = 30.dp),
        )
        UserDataTextFields(
            username = username,
            onUsernameChange = onUsernameChange,
            email = email,
            onEmailChange = onEmailChange,
            password = password,
            onPasswordChange = onPasswordChange,
            signUpState = signUpState,
            modifier = Modifier.padding(top = 35.dp),
        )
        SignUpButtons(
            modifier = Modifier.padding(top = 25.dp),
            onSignUpClick = onSignUpClick,
            onGoogleSignUpClick = onGoogleAuthClick,
            onFacebookSignUpClick = onFacebookAuthClick,
            onGithubSignUpClick = onGithubAuthClick,
        )
        SignInText(
            modifier = Modifier.padding(vertical = 40.dp),
            onSignInClick = onSignInClick,
        )
    }

    if (isLoadingState) {
        LoadingScreen()
    }

    if (signUpState is SignUpUiState.AuthError &&
        signUpState.cause !is EmailAlreadyInUseException) {
        Toast.makeText(
            LocalContext.current,
            signUpState.cause.toErrorStringId(),
            Toast.LENGTH_SHORT,
        ).show()
    }
}

@Composable
private fun UserDataTextFields(
    username: String,
    onUsernameChange: (String) -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    signUpState: SignUpUiState,
    modifier: Modifier
) {
    val textFieldModifier = Modifier.padding(horizontal = 25.dp)

    val validationErrorState = signUpState as? SignUpUiState.ValidationError
    val authErrorState = signUpState as? SignUpUiState.AuthError
    val usernameValidationError =
        validationErrorState?.usernameError?.toErrorStringId()?.let { stringResource(it) }
    val emailValidationError =
        (validationErrorState?.emailError?.toErrorStringId()
            ?: (authErrorState?.cause as? EmailAlreadyInUseException)?.toErrorStringId())
            ?.let { stringResource(it) }
    val passwordValidationError =
        validationErrorState?.passwordError?.toErrorStringId()?.let { stringResource(it) }

    val focusManager = LocalFocusManager.current
    Column(modifier = modifier) {
        UsernameTextField(
            username = username,
            onUsernameChange = onUsernameChange,
            error = usernameValidationError,
            imeAction = ImeAction.Next,
            modifier = textFieldModifier,
        )
        EmailTextField(
            email = email,
            onEmailChange = onEmailChange,
            error = emailValidationError,
            imeAction = ImeAction.Next,
            modifier = textFieldModifier.padding(top = 6.dp),
        )
        PasswordTextField(
            password = password,
            onPasswordChange = onPasswordChange,
            error = passwordValidationError,
            imeAction = ImeAction.Done,
            onImeAction = { focusManager.clearFocus() },
            modifier = textFieldModifier.padding(top = 6.dp),
        )
    }
}

@Composable
private fun SignUpButtons(
    onSignUpClick: () -> Unit,
    onGoogleSignUpClick: () -> Unit,
    onFacebookSignUpClick: () -> Unit,
    onGithubSignUpClick: () -> Unit,
    modifier: Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        FilledTextButton(
            modifier = Modifier
                .width(200.dp)
                .padding(horizontal = 20.dp),
            onClick = onSignUpClick,
        ) {
            Text(stringResource(R.string.sign_up))
        }
        Text(
            text = stringResource(R.string.continue_with_sso),
            modifier = Modifier.padding(top = 25.dp),
            style = MaterialTheme.typography.bodySmall,
        )
        Row(modifier = Modifier.padding(top = 5.dp)) {
            IdentityProviderSignInButton(
                R.drawable.ic_google_logo,
                onGoogleSignUpClick,
                Modifier.padding(horizontal = 6.dp),
            )
            IdentityProviderSignInButton(
                R.drawable.ic_facebook_logo,
                onFacebookSignUpClick,
                Modifier.padding(horizontal = 6.dp),
            )
            IdentityProviderSignInButton(
                R.drawable.ic_github_logo,
                onGithubSignUpClick,
                Modifier.padding(horizontal = 6.dp),
            )
        }
    }
}

@Composable
private fun IdentityProviderSignInButton(
    @DrawableRes id: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(onClick = onClick, modifier = modifier) {
        Image(
            painter = painterResource(id), contentDescription = null,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
            modifier = Modifier.size(40.dp),
        )
    }
}

@Composable
private fun SignInText(
    modifier: Modifier,
    onSignInClick: () -> Unit,
) {
    Row(
        modifier = modifier.padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.Center,
    ) {
        Text(
            text = stringResource(R.string.sign_in_question),
            style = MaterialTheme.typography.bodyMedium
        )
        TextButton(onClick = onSignInClick) {
            Text(
                text = stringResource(R.string.sign_in),
                modifier = Modifier.padding(start = 4.dp),
                textDecoration = TextDecoration.Underline,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
            )
        }
    }
}

@Preview
@Composable
private fun SignUpScreenPreview() {
    SignUpScreen()
}

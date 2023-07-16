package com.cbcds.aventura.feature.auth.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cbcds.aventura.core.common.exception.UnknownException
import com.cbcds.aventura.core.domain.SignInInteractor
import com.cbcds.aventura.core.domain.SsoInteractor
import com.cbcds.aventura.core.domain.ValidateUserDataInteractor
import com.cbcds.aventura.core.domain.model.EmailValidationError
import com.cbcds.aventura.core.domain.model.PasswordValidationError
import com.cbcds.aventura.core.domain.model.SignInDataValidationResult
import com.cbcds.aventura.core.navigation.NavigationController
import com.cbcds.aventura.core.navigation.Screen
import com.cbcds.aventura.feature.auth.navigation.FacebookSsoScreen
import com.cbcds.aventura.feature.auth.navigation.GithubSsoScreen
import com.cbcds.aventura.feature.auth.navigation.GoogleSsoScreen
import com.cbcds.aventura.feature.auth.navigation.SignUpScreen
import com.cbcds.aventura.feature.auth.signin.SignInUiState.ValidationErrors
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SignInViewModel @Inject constructor(
    private val validateUserDataInteractor: ValidateUserDataInteractor,
    private val signInInteractor: SignInInteractor,
    private val ssoInteractor: SsoInteractor,
    private val navigationController: NavigationController,
) : ViewModel() {

    private val _signInUiState = MutableStateFlow(SignInUiState())
    val signInUiState: StateFlow<SignInUiState>
        get() = _signInUiState

    private var signInJob: Job? = null

    fun signIn(email: String, password: String) {
        val validateDataResult = validateUserDataInteractor.validateSignInData(email, password)
        when (validateDataResult) {
            is SignInDataValidationResult.Success -> {
                _signInUiState.value = SignInUiState(showLoading = true)
                signInJob = viewModelScope.launch {
                    _signInUiState.value =
                        signInInteractor.signIn(email, password).toSignInUiState()
                }
            }
            is SignInDataValidationResult.Error -> {
                _signInUiState.value = SignInUiState(
                    validationErrors = ValidationErrors(
                        validateDataResult.emailError,
                        validateDataResult.passwordError,
                    )
                )
            }
        }
    }

    fun authWithGoogle() {
        authWithSso(
            ssoScreen = GoogleSsoScreen,
            authWithToken = ssoInteractor::authWithGoogle,
        )
    }

    fun authWithFacebook() {
        authWithSso(
            ssoScreen = FacebookSsoScreen,
            authWithToken = ssoInteractor::authWithFacebook,
        )
    }

    fun authWithGithub() {
        authWithSso(
            ssoScreen = GithubSsoScreen,
            authWithToken = ssoInteractor::authWithGithub,
        )
    }

    fun toSignUpScreen() {
        navigationController.navigateAndClearStack(SignUpScreen)
    }

    fun onBackClick() {
        val currentState = _signInUiState.value
        if (currentState.showLoading) {
            signInJob?.cancel()
            _signInUiState.value = _signInUiState.value.copy(showLoading = false, authError = null)
        }
    }

    private fun authWithSso(ssoScreen: Screen, authWithToken: suspend (String) -> Result<Unit>) {
        val resultFlow = navigationController.navigateForResult(ssoScreen)
        signInJob = viewModelScope.launch {
            resultFlow.collect {
                it.onSuccess { result ->
                    val token = result as? String
                    token?.let {
                        _signInUiState.value = _signInUiState.value
                            .copy(showLoading = true, authError = null)
                        _signInUiState.value = authWithToken(token).toSignInUiState()
                    }
                }.onFailure { error ->
                    _signInUiState.value =_signInUiState.value
                        .copy(showLoading = false, authError = error)
                }
            }
        }
    }

    private fun Result<Unit>.toSignInUiState(): SignInUiState {
        val authError = if (isFailure) exceptionOrNull() ?: UnknownException() else null
        return _signInUiState.value.copy(showLoading = false, authError = authError)
    }
}

internal data class SignInUiState(
    val showLoading: Boolean = false,
    val validationErrors: ValidationErrors? = null,
    val authError: Throwable? = null,
) {

    data class ValidationErrors(
        val emailError: EmailValidationError?,
        val passwordError: PasswordValidationError?,
    )
}

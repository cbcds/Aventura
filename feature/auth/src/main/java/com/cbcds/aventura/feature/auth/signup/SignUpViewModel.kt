package com.cbcds.aventura.feature.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cbcds.aventura.core.common.exception.UnknownException
import com.cbcds.aventura.core.domain.SignUpInteractor
import com.cbcds.aventura.core.domain.SsoInteractor
import com.cbcds.aventura.core.domain.ValidateUserDataInteractor
import com.cbcds.aventura.core.domain.model.EmailValidationError
import com.cbcds.aventura.core.domain.model.PasswordValidationError
import com.cbcds.aventura.core.domain.model.SignUpDataValidationResult
import com.cbcds.aventura.core.domain.model.UsernameValidationError
import com.cbcds.aventura.core.navigation.NavigationController
import com.cbcds.aventura.feature.auth.navigation.GoogleSsoScreen
import com.cbcds.aventura.feature.auth.navigation.SignInScreen
import com.cbcds.aventura.feature.auth.signup.SignUpUiState.ValidationErrors
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SignUpViewModel @Inject constructor(
    private val validateUserDataInteractor: ValidateUserDataInteractor,
    private val signUpInteractor: SignUpInteractor,
    private val ssoInteractor: SsoInteractor,
    private val navigationController: NavigationController,
) : ViewModel() {

    private val _signUpUiState = MutableStateFlow(SignUpUiState())
    val signUpUiState: StateFlow<SignUpUiState>
        get() = _signUpUiState

    var signUpJob: Job? = null

    fun signUp(username: String, email: String, password: String) {
        val validateDataResult =
            validateUserDataInteractor.validateSignUpData(username, email, password)
        when (validateDataResult) {
            is SignUpDataValidationResult.Success -> {
                _signUpUiState.value = SignUpUiState(showLoading = true)
                signUpJob = viewModelScope.launch {
                    _signUpUiState.value =
                        signUpInteractor.signUp(username, email, password).toSignUpUiState()
                }
            }
            is SignUpDataValidationResult.Error -> {
                _signUpUiState.value = SignUpUiState(
                    validationErrors = ValidationErrors(
                        validateDataResult.usernameError,
                        validateDataResult.emailError,
                        validateDataResult.passwordError,
                    )
                )
            }
        }
    }

    fun authWithGoogle() {
        val resultFlow = navigationController.navigateForResult(GoogleSsoScreen)
        signUpJob = viewModelScope.launch {
            resultFlow.collect {
                it.onSuccess { result ->
                    val token = result as? String
                    token?.let {
                        toSsoLoadingState()
                        _signUpUiState.value = ssoInteractor.authWithGoogle(token).toSignUpUiState()
                    }
                }.onFailure { error ->
                    toSsoErrorState(error)
                }
            }
        }
    }

    @Suppress("EmptyFunctionBlock")
    fun authWithFacebook() {}

    @Suppress("EmptyFunctionBlock")
    fun authWithGithub() {}

    fun toSignInScreen() {
        navigationController.navigateAndClearStack(SignInScreen)
    }

    fun onBackClick() {
        val currentState = _signUpUiState.value
        if (currentState.showLoading) {
            signUpJob?.cancel()
            _signUpUiState.value = currentState.copy(showLoading = false)
        }
    }

    private fun toSsoLoadingState() {
        _signUpUiState.value = _signUpUiState.value.copy(showLoading = true, authError = null)
    }

    private fun toSsoErrorState(authError: Throwable) {
        _signUpUiState.value =_signUpUiState.value.copy(showLoading = false, authError = authError)
    }

    private fun Result<Unit>.toSignUpUiState(): SignUpUiState {
        val authError = if (isFailure) exceptionOrNull() ?: UnknownException() else null
        return _signUpUiState.value.copy(showLoading = false, authError = authError)
    }
}

internal data class SignUpUiState(
    val showLoading: Boolean = false,
    val validationErrors: ValidationErrors? = null,
    val authError: Throwable? = null,
) {

    data class ValidationErrors(
        val usernameError: UsernameValidationError?,
        val emailError: EmailValidationError?,
        val passwordError: PasswordValidationError?,
    )
}

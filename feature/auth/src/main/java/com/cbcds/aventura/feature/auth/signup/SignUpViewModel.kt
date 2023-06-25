package com.cbcds.aventura.feature.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cbcds.aventura.core.domain.SignUpUseCase
import com.cbcds.aventura.core.domain.SsoUseCase
import com.cbcds.aventura.core.domain.ValidateUserDataUseCase
import com.cbcds.aventura.core.domain.model.EmailValidationResult
import com.cbcds.aventura.core.domain.model.PasswordValidationResult
import com.cbcds.aventura.core.domain.model.SignUpDataValidationResult
import com.cbcds.aventura.core.domain.model.SignUpState
import com.cbcds.aventura.core.domain.model.SsoState
import com.cbcds.aventura.core.domain.model.UsernameValidationResult
import com.cbcds.aventura.core.navigation.NavigationController
import com.cbcds.aventura.feature.auth.navigation.GoogleSsoScreen
import com.cbcds.aventura.feature.auth.navigation.SignInScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SignUpViewModel @Inject constructor(
    private val validateUserDataUseCase: ValidateUserDataUseCase,
    private val signUpUseCase: SignUpUseCase,
    private val ssoUseCase: SsoUseCase,
    private val navigationController: NavigationController,
) : ViewModel() {

    private val _signUpUiState = MutableStateFlow<SignUpUiState>(SignUpUiState.Initial())
    val signUpUiState: StateFlow<SignUpUiState>
        get() = _signUpUiState

    var signUpJob: Job? = null

    fun signUp(username: String, email: String, password: String) {
        val validateDataResult =
            validateUserDataUseCase.validateSignUpData(username, email, password)
        when (validateDataResult) {
            is SignUpDataValidationResult.Success -> {
                toStateWithLoading(SignUpUiState.Initial())
                signUpJob = viewModelScope.launch {
                    _signUpUiState.value =
                        signUpUseCase.signUp(username, email, password).toSignUpUiState()
                }
            }
            is SignUpDataValidationResult.Error -> {
                _signUpUiState.value = SignUpUiState.ValidationError(
                    validateDataResult.usernameError,
                    validateDataResult.emailError,
                    validateDataResult.passwordError,
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
                    toStateWithLoading(SignUpUiState.Initial())
                    _signUpUiState.value = ssoUseCase.authWithGoogle(token).toSignUpUiState()
                }.onFailure { error ->
                    _signUpUiState.value = SignUpUiState.AuthError(error)
                }
            }
        }
    }

    fun authWithFacebook() { }

    fun authWithGithub() { }

    fun toSignInScreen() {
        navigationController.navigateAndClearStack(SignInScreen)
    }

    fun onBackClick() {
        val currentState = _signUpUiState.value
        if (currentState is SignUpUiState.Initial && currentState.showLoading) {
            signUpJob?.cancel()
            _signUpUiState.value = currentState.copy(showLoading = false)
        }
        if (currentState is SignUpUiState.ValidationError && currentState.showLoading) {
            signUpJob?.cancel()
            _signUpUiState.value = currentState.copy(showLoading = false)
        }
    }

    private fun toStateWithLoading(baseState: SignUpUiState) {
        if (baseState is SignUpUiState.Initial) {
            _signUpUiState.value = baseState.copy(showLoading = true)
        }
        if (baseState is SignUpUiState.ValidationError) {
            _signUpUiState.value = baseState.copy(showLoading = true)
        }
    }

    private fun SignUpState.toSignUpUiState(): SignUpUiState {
        return when (this) {
            is SignUpState.Success -> SignUpUiState.Success
            is SignUpState.Error -> SignUpUiState.AuthError(cause)
        }
    }

    private fun SsoState.toSignUpUiState(): SignUpUiState {
        return when (this) {
            is SsoState.Success -> SignUpUiState.Success
            is SsoState.Error -> SignUpUiState.AuthError(cause)
        }
    }
}

internal sealed interface SignUpUiState {

    data class Initial(
        val showLoading: Boolean = false,
    ) : SignUpUiState

    object Success : SignUpUiState

    data class ValidationError(
        val usernameError: UsernameValidationResult.UsernameError?,
        val emailError: EmailValidationResult.EmailError?,
        val passwordError: PasswordValidationResult.PasswordError?,
        val showLoading: Boolean = false,
    ) : SignUpUiState

    data class AuthError(val cause: Throwable) : SignUpUiState
}

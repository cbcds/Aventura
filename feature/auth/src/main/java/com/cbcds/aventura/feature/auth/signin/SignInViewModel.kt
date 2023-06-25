package com.cbcds.aventura.feature.auth.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cbcds.aventura.core.domain.SignInUseCase
import com.cbcds.aventura.core.domain.SsoUseCase
import com.cbcds.aventura.core.domain.ValidateUserDataUseCase
import com.cbcds.aventura.core.domain.model.EmailValidationResult
import com.cbcds.aventura.core.domain.model.PasswordValidationResult
import com.cbcds.aventura.core.domain.model.SignInDataValidationResult
import com.cbcds.aventura.core.domain.model.SignInState
import com.cbcds.aventura.core.domain.model.SsoState
import com.cbcds.aventura.core.navigation.NavigationController
import com.cbcds.aventura.feature.auth.navigation.GoogleSsoScreen
import com.cbcds.aventura.feature.auth.navigation.SignUpScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val validateUserDataUseCase: ValidateUserDataUseCase,
    private val signInUseCase: SignInUseCase,
    private val ssoUseCase: SsoUseCase,
    private val navigationController: NavigationController,
) : ViewModel() {

    private val _signInUiState = MutableStateFlow<SignInUiState>(SignInUiState.Initial())
    val signInUiState: StateFlow<SignInUiState>
        get() = _signInUiState

    private var signInJob: Job? = null

    fun signIn(email: String, password: String) {
        val validateDataResult = validateUserDataUseCase.validateSignInData(email, password)
        when (validateDataResult) {
            is SignInDataValidationResult.Success -> {
                toStateWithLoading(SignInUiState.Initial())
                signInJob = viewModelScope.launch {
                    _signInUiState.value = signInUseCase.signIn(email, password).toSignInUiState()
                }
            }
            is SignInDataValidationResult.Error -> {
                _signInUiState.value = SignInUiState.ValidationError(
                    validateDataResult.emailError,
                    validateDataResult.passwordError
                )
            }
        }
    }

    fun authWithGoogle() {
        val resultFlow = navigationController.navigateForResult(GoogleSsoScreen)
        signInJob = viewModelScope.launch {
            resultFlow.collect {
                it.onSuccess { result ->
                    val token = result as? String
                    toStateWithLoading(SignInUiState.Initial())
                    _signInUiState.value = ssoUseCase.authWithGoogle(token).toSignInUiState()
                }.onFailure { error ->
                    _signInUiState.value = SignInUiState.AuthError(error)
                }
            }
        }
    }

    fun authWithFacebook() {}

    fun authWithGithub() {}

    fun toSignUpScreen() {
        navigationController.navigateAndClearStack(SignUpScreen)
    }

    fun onBackClick() {
        val currentState = _signInUiState.value
        if (currentState is SignInUiState.Initial && currentState.showLoading) {
            signInJob?.cancel()
            _signInUiState.value = currentState.copy(showLoading = false)
        }
        if (currentState is SignInUiState.ValidationError && currentState.showLoading) {
            signInJob?.cancel()
            _signInUiState.value = currentState.copy(showLoading = false)
        }
    }

    private fun toStateWithLoading(baseState: SignInUiState) {
        if (baseState is SignInUiState.Initial) {
            _signInUiState.value = baseState.copy(showLoading = true)
        }
        if (baseState is SignInUiState.ValidationError) {
            _signInUiState.value = baseState.copy(showLoading = true)
        }
    }

    private fun SignInState.toSignInUiState(): SignInUiState {
        return when (this) {
            is SignInState.Success -> SignInUiState.Success
            is SignInState.Error -> SignInUiState.AuthError(cause)
        }
    }

    private fun SsoState.toSignInUiState(): SignInUiState {
        return when (this) {
            is SsoState.Success -> SignInUiState.Success
            is SsoState.Error -> SignInUiState.AuthError(cause)
        }
    }
}

sealed interface SignInUiState {

    data class Initial(
        val showLoading: Boolean = false,
    ) : SignInUiState

    object Success : SignInUiState

    data class ValidationError(
        val emailError: EmailValidationResult.EmailError?,
        val passwordError: PasswordValidationResult.PasswordError?,
        val showLoading: Boolean = false,
    ) : SignInUiState

    data class AuthError(val cause: Throwable) : SignInUiState
}

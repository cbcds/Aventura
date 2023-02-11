package com.cbcds.aventura.feature.auth.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cbcds.aventura.core.domain.SignInUseCase
import com.cbcds.aventura.core.domain.ValidateUserDataUseCase
import com.cbcds.aventura.core.domain.model.EmailValidationResult
import com.cbcds.aventura.core.domain.model.PasswordValidationResult
import com.cbcds.aventura.core.domain.model.SignInDataValidationResult
import com.cbcds.aventura.core.domain.model.SignInState
import com.cbcds.aventura.core.navigation.NavigationManager
import com.cbcds.aventura.feature.auth.navigation.SignUpScreen
import com.cbcds.aventura.feature.auth.signup.SignUpUiState
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
    private val navigationManager: NavigationManager,
) : ViewModel() {

    private val _signInUiState = MutableStateFlow<SignInUiState>(SignInUiState.Initial())
    val signInUiState: StateFlow<SignInUiState>
        get() = _signInUiState

    var signInJob: Job? = null

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

    fun signInWithGoogle() {
        toStateWithLoading(_signInUiState.value)
        signInJob = viewModelScope.launch {
            _signInUiState.value = signInUseCase.signInWithGoogle().toSignInUiState()
        }
    }

    fun signInWithFacebook() {
        toStateWithLoading(_signInUiState.value)
        signInJob = viewModelScope.launch {
            _signInUiState.value = signInUseCase.signInWithFacebook().toSignInUiState()
        }
    }

    fun signInWithGithub() {
        toStateWithLoading(_signInUiState.value)
        signInJob = viewModelScope.launch {
            _signInUiState.value = signInUseCase.signInWithGithub().toSignInUiState()
        }
    }

    fun toSignUpScreen() {
        navigationManager.navigateAndClearStack(SignUpScreen)
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
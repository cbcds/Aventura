package com.cbcds.aventura.feature.auth.sso

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cbcds.aventura.core.common.utils.runSuspendCatching
import com.cbcds.aventura.core.data.api.GoogleSignInClientFacade
import com.cbcds.aventura.core.navigation.NavigationController
import com.cbcds.aventura.feature.auth.BuildConfig
import com.google.android.gms.auth.api.identity.BeginSignInResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class GoogleAuthViewModel @Inject constructor(
    private val googleSignInClientFacade: GoogleSignInClientFacade,
    private val navigationController: NavigationController,
) : ViewModel() {

    private val _googleAuthUiState = MutableStateFlow<GoogleAuthUiState>(GoogleAuthUiState.Initial)
    val googleAuthUiState: StateFlow<GoogleAuthUiState>
        get() = _googleAuthUiState

    init {
        beginAuth()
    }

    fun auth(signInResultData: Intent?) {
        runCatching {
            val token = googleSignInClientFacade.getToken(signInResultData)
            navigationController.finishWithResult(Result.success(token))
        }.onFailure {
            finishWithError(it)
        }
    }

    fun cancel() {
        navigationController.finishWithResult(Result.success(null))
    }

    private fun finishWithError(exception: Throwable) {
        navigationController.finishWithResult(Result.failure<String?>(exception))
    }

    private fun beginAuth() {
        viewModelScope.launch {
            runSuspendCatching {
                googleSignInClientFacade.beginSignIn(BuildConfig.GOOGLE_CLID)
            }.onSuccess {
                _googleAuthUiState.value = GoogleAuthUiState.ReadyToShowAuth(it)
            }.onFailure {
                finishWithError(it)
            }
        }
    }
}

internal sealed interface GoogleAuthUiState {

    object Initial : GoogleAuthUiState

    data class ReadyToShowAuth(val beginSignInResult: BeginSignInResult) : GoogleAuthUiState
}

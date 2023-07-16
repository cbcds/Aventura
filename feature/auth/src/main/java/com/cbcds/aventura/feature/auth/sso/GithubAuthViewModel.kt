package com.cbcds.aventura.feature.auth.sso

import androidx.lifecycle.ViewModel
import com.cbcds.aventura.core.navigation.NavigationController
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.OAuthCredential
import com.google.firebase.auth.OAuthProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class GithubAuthViewModel @Inject constructor(
    private val navigationController: NavigationController,
) : ViewModel() {

    val oAuthProvider: OAuthProvider = OAuthProvider.newBuilder("github.com").build()

    fun auth(authResult: AuthResult) {
        val token = (authResult.credential as? OAuthCredential)?.accessToken
        navigationController.finishWithResult(Result.success(token))
    }

    fun cancel() {
        navigationController.finishWithResult(Result.success(null))
    }

    fun finish(exception: Exception) {
        navigationController.finishWithResult(Result.failure<String?>(exception))
    }
}

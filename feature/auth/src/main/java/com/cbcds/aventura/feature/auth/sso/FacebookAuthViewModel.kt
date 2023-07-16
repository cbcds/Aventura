package com.cbcds.aventura.feature.auth.sso

import androidx.lifecycle.ViewModel
import com.cbcds.aventura.core.navigation.NavigationController
import com.facebook.login.LoginResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class FacebookAuthViewModel @Inject constructor(
    private val navigationController: NavigationController,
) : ViewModel() {

    val permissions: List<String> = listOf("email", "public_profile")

    fun auth(loginResult: LoginResult) {
        val token = loginResult.accessToken.token
        navigationController.finishWithResult(Result.success(token))
    }

    fun cancel() {
        navigationController.finishWithResult(Result.success(null))
    }

    fun finish(exception: Exception) {
        navigationController.finishWithResult(Result.failure<String?>(exception))
    }
}

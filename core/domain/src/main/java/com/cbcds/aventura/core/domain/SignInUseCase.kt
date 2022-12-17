package com.cbcds.aventura.core.domain

import com.cbcds.aventura.core.domain.model.SignInState
import javax.inject.Inject

class SignInUseCase @Inject constructor() {

    suspend fun signIn(email: String, password: String): SignInState {
        return SignInState.Success
    }

    suspend fun signInWithGoogle(): SignInState {
        return SignInState.Success
    }

    suspend fun signInWithFacebook(): SignInState {
        return SignInState.Success
    }

    suspend fun signInWithGithub(): SignInState {
        return SignInState.Success
    }
}
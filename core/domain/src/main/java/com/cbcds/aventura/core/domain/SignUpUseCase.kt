package com.cbcds.aventura.core.domain

import com.cbcds.aventura.core.domain.model.SignUpState
import javax.inject.Inject

class SignUpUseCase @Inject constructor() {

    suspend fun signUp(username: String, email: String, password: String): SignUpState {
        return SignUpState.Success
    }

    suspend fun signUpWithGoogle(): SignUpState {
        return SignUpState.Success
    }

    suspend fun signUpWithFacebook(): SignUpState {
        return SignUpState.Success
    }

    suspend fun signUpWithGithub(): SignUpState {
        return SignUpState.Success
    }
}
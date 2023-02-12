package com.cbcds.aventura.core.domain

import com.cbcds.aventura.core.common.utils.runSuspendCatching
import com.cbcds.aventura.core.data.api.UserRepository
import com.cbcds.aventura.core.domain.model.SignUpState
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {

    suspend fun signUp(username: String, email: String, password: String): SignUpState {
        return runSuspendCatching {
            userRepository.signUpWithEmailAndPassword(email, password)
            userRepository.updateUsername(username)
            SignUpState.Success
        }.getOrElse {
            SignUpState.Error(it)
        }
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
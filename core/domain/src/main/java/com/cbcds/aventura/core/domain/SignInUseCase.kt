package com.cbcds.aventura.core.domain

import com.cbcds.aventura.core.common.utils.runSuspendCatching
import com.cbcds.aventura.core.data.api.UserRepository
import com.cbcds.aventura.core.domain.model.SignInState
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {

    suspend fun signIn(email: String, password: String): SignInState {
        return runSuspendCatching {
            userRepository.signInWithEmailAndPassword(email, password)
            SignInState.Success
        }.getOrElse {
            SignInState.Error(it)
        }
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
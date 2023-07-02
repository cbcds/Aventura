package com.cbcds.aventura.core.domain

import com.cbcds.aventura.core.common.utils.runSuspendCatching
import com.cbcds.aventura.core.data.api.UserRepository
import javax.inject.Inject

class SignInInteractor @Inject constructor(
    private val userRepository: UserRepository,
) {

    suspend fun signIn(email: String, password: String): Result<Unit> {
        return runSuspendCatching {
            userRepository.signInWithEmailAndPassword(email, password)
            Result.success(Unit)
        }.getOrElse {
            Result.failure(it)
        }
    }
}

package com.cbcds.aventura.core.domain

import com.cbcds.aventura.core.common.utils.runSuspendCatching
import com.cbcds.aventura.core.data.api.UserRepository
import javax.inject.Inject

class SignUpInteractor @Inject constructor(
    private val userRepository: UserRepository,
) {

    suspend fun signUp(username: String, email: String, password: String): Result<Unit> {
        return runSuspendCatching {
            userRepository.signUpWithEmailAndPassword(email, password)
            userRepository.updateUsername(username)
            Result.success(Unit)
        }.getOrElse {
            Result.failure(it)
        }
    }
}

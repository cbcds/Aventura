package com.cbcds.aventura.core.domain

import com.cbcds.aventura.core.common.utils.runSuspendCatching
import com.cbcds.aventura.core.data.api.UserRepository
import javax.inject.Inject

class SsoInteractor @Inject constructor(
    private val userRepository: UserRepository,
) {

    suspend fun authWithGoogle(token: String): Result<Unit> {
        return authWithSso {
            userRepository.authWithGoogle(token)
        }
    }

    suspend fun authWithFacebook(token: String): Result<Unit> {
        return authWithSso {
            userRepository.authWithFacebook(token)
        }
    }

    @Suppress("UnusedPrivateMember")
    suspend fun authWithGitHub(token: String): Result<Unit> {
        return Result.success(Unit)
    }

    private suspend fun authWithSso(auth: suspend () -> Unit): Result<Unit> {
        return runSuspendCatching {
            auth()
            Result.success(Unit)
        }.getOrElse {
            Result.failure(it)
        }
    }
}

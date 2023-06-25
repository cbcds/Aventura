package com.cbcds.aventura.core.domain

import com.cbcds.aventura.core.common.utils.runSuspendCatching
import com.cbcds.aventura.core.data.api.UserRepository
import com.cbcds.aventura.core.domain.model.SsoState
import javax.inject.Inject

class SsoUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {

    suspend fun authWithGoogle(token: String?): SsoState {
        return runSuspendCatching {
            userRepository.authWithGoogle(token)
            SsoState.Success
        }.getOrElse {
            SsoState.Error(it)
        }
    }

    suspend fun authWithFacebook(token: String): SsoState {
        return SsoState.Success
    }

    suspend fun authWithGitHub(token: String): SsoState {
        return SsoState.Success
    }
}

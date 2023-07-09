package com.cbcds.aventura.core.domain

import com.cbcds.aventura.core.common.utils.runSuspendCatching
import com.cbcds.aventura.core.data.api.UserRepository
import javax.inject.Inject

class SignOutInteractor @Inject constructor(
    private val userRepository: UserRepository,
) {

    suspend fun signOut() {
        runSuspendCatching {
            userRepository.signOut()
        }
    }
}

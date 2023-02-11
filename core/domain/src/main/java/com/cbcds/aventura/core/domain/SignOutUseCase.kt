package com.cbcds.aventura.core.domain

import com.cbcds.aventura.core.data.api.UserRepository
import javax.inject.Inject

class SignOutUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {

    suspend fun signOut() {
        userRepository.signOut()
    }
}
package com.cbcds.aventura.core.data.internal

import com.cbcds.aventura.core.data.api.UserRepository
import com.cbcds.aventura.core.network.api.UserApi
import javax.inject.Inject

class DefaultUserRepository @Inject constructor(
    private val userApi: UserApi,
) : UserRepository {

    override suspend fun signUpWithEmailAndPassword(email: String, password: String) {
        userApi.signUpWithEmailAndPassword(email, password)
    }

    override suspend fun updateUsername(username: String) {
        userApi.updateUsername(username)
    }
}
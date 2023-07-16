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

    override suspend fun signInWithEmailAndPassword(email: String, password: String) {
        userApi.signInWithEmailAndPassword(email, password)
    }

    override suspend fun authWithGoogle(token: String) {
        userApi.authWithGoogle(token)
    }

    override suspend fun authWithFacebook(token: String) {
        userApi.authWithFacebook(token)
    }

    override suspend fun signOut() {
        userApi.signOut()
    }

    override suspend fun updateUsername(username: String) {
        userApi.updateUsername(username)
    }
}

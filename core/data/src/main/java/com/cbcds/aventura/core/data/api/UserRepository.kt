package com.cbcds.aventura.core.data.api

interface UserRepository {

    suspend fun signUpWithEmailAndPassword(email: String, password: String)

    suspend fun signInWithEmailAndPassword(email: String, password: String)

    suspend fun authWithGoogle(token: String)

    suspend fun authWithFacebook(token: String)

    suspend fun signOut()

    suspend fun updateUsername(username: String)
}

package com.cbcds.aventura.core.network.api

interface UserApi {

    suspend fun signUpWithEmailAndPassword(email: String, password: String)

    suspend fun signInWithEmailAndPassword(email: String, password: String)

    suspend fun signOut()

    suspend fun updateUsername(username: String)
}
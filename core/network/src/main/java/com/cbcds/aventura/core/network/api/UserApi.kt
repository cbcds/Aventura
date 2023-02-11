package com.cbcds.aventura.core.network.api

interface UserApi {

    suspend fun signUpWithEmailAndPassword(email: String, password: String)

    suspend fun updateUsername(username: String)
}
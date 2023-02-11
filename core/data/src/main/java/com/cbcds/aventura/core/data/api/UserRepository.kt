package com.cbcds.aventura.core.data.api

interface UserRepository {

    suspend fun signUpWithEmailAndPassword(email: String, password: String)

    suspend fun updateUsername(username: String)
}
package com.cbcds.aventura.core.network.internal.user

import com.cbcds.aventura.core.network.api.UserApi
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseUserApi @Inject constructor() : UserApi {

    init {
        Firebase.auth.useEmulator("10.0.2.2", 9099)
    }

    override suspend fun signUpWithEmailAndPassword(email: String, password: String) {
        Firebase.auth.createUserWithEmailAndPassword(email, password)
            .addOnFailureListener {
                throw when (it) {
                    is FirebaseAuthException -> it.toAuthException()
                    else -> it
                }
            }
            .await()
    }

    override suspend fun signInWithEmailAndPassword(email: String, password: String) {
        Firebase.auth.signInWithEmailAndPassword(email, password)
            .addOnFailureListener {
                throw when (it) {
                    is FirebaseAuthException -> it.toAuthException()
                    else -> it
                }
            }
            .await()
    }

    override suspend fun signOut() {
        Firebase.auth.signOut()
    }

    override suspend fun updateUsername(username: String) {
        Firebase.auth.currentUser?.updateProfile(
            UserProfileChangeRequest.Builder().setDisplayName(username).build()
        )?.await()
    }
}

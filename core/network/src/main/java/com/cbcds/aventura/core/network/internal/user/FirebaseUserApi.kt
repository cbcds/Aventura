package com.cbcds.aventura.core.network.internal.user

import com.cbcds.aventura.core.network.api.UserApi
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resumeWithException

class FirebaseUserApi @Inject constructor() : UserApi {

    init {
        Firebase.auth.useEmulator("10.0.2.2", 9099)
    }

    override suspend fun signUpWithEmailAndPassword(email: String, password: String) =
        suspendCancellableCoroutine { continuation ->
            Firebase.auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    continuation.resumeWith(Result.success(Unit))
                }.addOnFailureListener {
                    val exception = when (it) {
                        is FirebaseAuthException -> it.toAuthException()
                        else -> it
                    }
                    continuation.resumeWithException(exception)
                }
            // TODO invoke on cancellation
        }

    override suspend fun updateUsername(username: String) =
        suspendCancellableCoroutine { continuation ->
            // TODO exceptions handling
            Firebase.auth.currentUser?.updateProfile(
                UserProfileChangeRequest.Builder().setDisplayName(username).build()
            )?.addOnSuccessListener {
                continuation.resumeWith(Result.success(Unit))
            }?.addOnFailureListener {
                continuation.resumeWithException(it)
            }
        }
}

package com.cbcds.aventura.core.network.internal.user

import com.cbcds.aventura.core.common.exception.UnknownException
import com.cbcds.aventura.core.common.utils.runSuspendCatching
import com.cbcds.aventura.core.network.BuildConfig
import com.cbcds.aventura.core.network.firebase.FirebaseEmulator
import com.cbcds.aventura.core.network.api.UserApi
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseUserApi @Inject constructor() : UserApi {

    init {
        if (BuildConfig.DEBUG) {
            FirebaseEmulator.enableAuth()
        }
    }

    override suspend fun signUpWithEmailAndPassword(email: String, password: String) {
        runSuspendCatching {
            Firebase.auth.createUserWithEmailAndPassword(email, password).await()
        }.onFailure {
            throw when (it) {
                is FirebaseAuthException -> it.toAventuraException()
                else -> UnknownException()
            }
        }
    }

    override suspend fun signInWithEmailAndPassword(email: String, password: String) {
        runSuspendCatching {
            Firebase.auth.signInWithEmailAndPassword(email, password).await()
        }.onFailure {
            throw when (it) {
                is FirebaseAuthException -> it.toAventuraException()
                else -> UnknownException()
            }
        }
    }

    override suspend fun authWithGoogle(token: String?) {
        runSuspendCatching {
            val firebaseCredential = GoogleAuthProvider.getCredential(token, null)
            Firebase.auth.signInWithCredential(firebaseCredential).await()
        }.onFailure {
            throw when (it) {
                is FirebaseAuthException -> it.toAventuraException()
                else -> UnknownException()
            }
        }
    }

    override suspend fun signOut() {
        Firebase.auth.signOut()
    }

    override suspend fun updateUsername(username: String) {
        runSuspendCatching {
            Firebase.auth.currentUser
                ?.updateProfile(
                    UserProfileChangeRequest.Builder().setDisplayName(username).build()
                )
                ?.await()
        }.onFailure {
            throw UnknownException()
        }
    }
}

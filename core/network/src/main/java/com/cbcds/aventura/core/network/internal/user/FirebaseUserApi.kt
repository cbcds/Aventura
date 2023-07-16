package com.cbcds.aventura.core.network.internal.user

import com.cbcds.aventura.core.common.exception.UnknownException
import com.cbcds.aventura.core.common.utils.runSuspendCatching
import com.cbcds.aventura.core.network.BuildConfig
import com.cbcds.aventura.core.network.api.UserApi
import com.cbcds.aventura.core.network.firebase.FirebaseEmulator
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.GithubAuthProvider
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
                else -> UnknownException(it)
            }
        }
    }

    override suspend fun signInWithEmailAndPassword(email: String, password: String) {
        runSuspendCatching {
            Firebase.auth.signInWithEmailAndPassword(email, password).await()
        }.onFailure {
            throw when (it) {
                is FirebaseAuthException -> it.toAventuraException()
                else -> UnknownException(it)
            }
        }
    }

    override suspend fun authWithGoogle(token: String) {
        authWithSso {
            GoogleAuthProvider.getCredential(token, null)
        }
    }

    override suspend fun authWithFacebook(token: String) {
        authWithSso {
            FacebookAuthProvider.getCredential(token)
        }
    }

    override suspend fun authWithGithub(token: String) {
        authWithSso {
            GithubAuthProvider.getCredential(token)
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
            throw UnknownException(it)
        }
    }

    private suspend fun authWithSso(getCredential: () -> AuthCredential) {
        runSuspendCatching {
            Firebase.auth.signInWithCredential(getCredential()).await()
        }.onFailure {
            throw when (it) {
                is FirebaseAuthException -> it.toAventuraException()
                else -> UnknownException(it)
            }
        }
    }
}

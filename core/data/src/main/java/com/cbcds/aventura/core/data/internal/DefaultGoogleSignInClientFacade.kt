package com.cbcds.aventura.core.data.internal

import android.content.Intent
import android.util.Log
import com.cbcds.aventura.core.common.exception.AventuraException
import com.cbcds.aventura.core.common.exception.UnknownException
import com.cbcds.aventura.core.data.api.GoogleSignInClientFacade
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.auth.api.identity.SignInClient
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class DefaultGoogleSignInClientFacade @Inject constructor(
    private val signInClient: SignInClient,
) : GoogleSignInClientFacade {

    override suspend fun beginSignIn(serverClientId: String): BeginSignInResult {
        return try {
            signInClient.beginSignIn(createSignInRequest(serverClientId)).await()
        } catch (e: Exception) {
            try {
                signInClient.beginSignIn(createSignUpRequest(serverClientId)).await()
            } catch (e: Exception) {
                throw e.toAventuraException()
            }
        }
    }

    override fun getToken(signInResultData: Intent?): String? {
        return signInClient.getSignInCredentialFromIntent(signInResultData).googleIdToken
    }

    private fun createSignInRequest(serverClientId: String): BeginSignInRequest {
        return BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId(serverClientId)
                    .setFilterByAuthorizedAccounts(true)
                    .build()
            )
            .setAutoSelectEnabled(false)
            .build()
    }

    private fun createSignUpRequest(serverClientId: String): BeginSignInRequest {
        return BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId(serverClientId)
                    .setFilterByAuthorizedAccounts(false)
                    .build()
            )
            .setAutoSelectEnabled(false)
            .build()
    }

    private fun Exception.toAventuraException(): AventuraException {
        return UnknownException()
    }
}

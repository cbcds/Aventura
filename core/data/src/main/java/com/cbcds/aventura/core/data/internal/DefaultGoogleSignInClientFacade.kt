package com.cbcds.aventura.core.data.internal

import android.content.Intent
import com.cbcds.aventura.core.common.exception.NoMatchingCredentialsException
import com.cbcds.aventura.core.common.exception.UnknownException
import com.cbcds.aventura.core.data.api.GoogleSignInClientFacade
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class DefaultGoogleSignInClientFacade @Inject constructor(
    private val signInClient: SignInClient,
) : GoogleSignInClientFacade {

    @Suppress("TooGenericExceptionCaught", "SwallowedException")
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
        return runCatching {
            signInClient.getSignInCredentialFromIntent(signInResultData).googleIdToken
        }.onFailure {
            throw it.toAventuraException()
        }.getOrNull()
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

    private fun Throwable.toAventuraException(): Exception {
        return if (this is ApiException && statusCode == CommonStatusCodes.CANCELED) {
            NoMatchingCredentialsException
        } else {
            UnknownException(this)
        }
    }
}

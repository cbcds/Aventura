package com.cbcds.aventura.core.data.api

import android.content.Intent
import com.google.android.gms.auth.api.identity.BeginSignInResult

interface GoogleSignInClientFacade {

    suspend fun beginSignIn(serverClientId: String): BeginSignInResult

    fun getToken(signInResultData: Intent?): String?
}
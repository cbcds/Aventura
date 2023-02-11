package com.cbcds.aventura.core.network.internal.user

import com.cbcds.aventura.core.common.exception.AuthException
import com.cbcds.aventura.core.common.exception.EmailAlreadyInUseException
import com.cbcds.aventura.core.common.exception.UnknownException
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthUserCollisionException

fun FirebaseAuthException.toAuthException(): AuthException {
    return when (this) {
        is FirebaseAuthUserCollisionException -> toAuthException()
        else -> UnknownException()
    }
}

private fun FirebaseAuthUserCollisionException.toAuthException(): AuthException {
    return when (this.errorCode) {
        "ERROR_EMAIL_ALREADY_IN_USE" -> EmailAlreadyInUseException()
        else -> UnknownException()
    }
}
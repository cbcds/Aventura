package com.cbcds.aventura.core.network.internal.user

import com.cbcds.aventura.core.common.exception.AuthException
import com.cbcds.aventura.core.common.exception.EmailAlreadyInUseException
import com.cbcds.aventura.core.common.exception.InvalidPasswordException
import com.cbcds.aventura.core.common.exception.UnknownException
import com.cbcds.aventura.core.common.exception.UserDisabledException
import com.cbcds.aventura.core.common.exception.UserNotFoundException
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException

fun FirebaseAuthException.toAuthException(): AuthException {
    return when (this) {
        is FirebaseAuthUserCollisionException -> toAuthException()
        is FirebaseAuthInvalidUserException -> toAuthException()
        is FirebaseAuthInvalidCredentialsException -> toAuthException()
        else -> UnknownException()
    }
}

private fun FirebaseAuthUserCollisionException.toAuthException(): AuthException {
    return when (this.errorCode) {
        "ERROR_EMAIL_ALREADY_IN_USE" -> EmailAlreadyInUseException()
        else -> UnknownException()
    }
}

private fun FirebaseAuthInvalidUserException.toAuthException(): AuthException {
    return when (this.errorCode) {
        "ERROR_USER_DISABLED" -> UserDisabledException()
        "ERROR_USER_NOT_FOUND" -> UserNotFoundException()
        else -> UnknownException()
    }
}

private fun FirebaseAuthInvalidCredentialsException.toAuthException(): AuthException {
    return InvalidPasswordException()
}
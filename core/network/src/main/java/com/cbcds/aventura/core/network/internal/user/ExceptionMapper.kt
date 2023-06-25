package com.cbcds.aventura.core.network.internal.user

import com.cbcds.aventura.core.common.exception.AuthException
import com.cbcds.aventura.core.common.exception.AventuraException
import com.cbcds.aventura.core.common.exception.EmailAlreadyInUseException
import com.cbcds.aventura.core.common.exception.InvalidPasswordException
import com.cbcds.aventura.core.common.exception.UnknownException
import com.cbcds.aventura.core.common.exception.UserDisabledException
import com.cbcds.aventura.core.common.exception.UserNotFoundException
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException

fun FirebaseAuthException.toAventuraException(): AventuraException {
    return when (this) {
        is FirebaseAuthUserCollisionException -> toAventuraException()
        is FirebaseAuthInvalidUserException -> toAventuraException()
        is FirebaseAuthInvalidCredentialsException -> toAventuraException()
        else -> UnknownException()
    }
}

private fun FirebaseAuthUserCollisionException.toAventuraException(): AventuraException {
    return when (this.errorCode) {
        "ERROR_EMAIL_ALREADY_IN_USE" -> EmailAlreadyInUseException
        else -> UnknownException()
    }
}

private fun FirebaseAuthInvalidUserException.toAventuraException(): AventuraException {
    return when (this.errorCode) {
        "ERROR_USER_DISABLED" -> UserDisabledException
        "ERROR_USER_NOT_FOUND" -> UserNotFoundException
        else -> UnknownException()
    }
}

private fun FirebaseAuthInvalidCredentialsException.toAventuraException(): AuthException {
    return InvalidPasswordException
}

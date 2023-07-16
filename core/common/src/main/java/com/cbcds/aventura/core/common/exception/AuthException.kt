package com.cbcds.aventura.core.common.exception

sealed class AuthException : Exception()

object EmailAlreadyInUseException : AuthException()

object UserDisabledException : AuthException()

object UserNotFoundException : AuthException()

object InvalidPasswordException : AuthException()

object AccountExistsWithDifferentProvider : AuthException()

object NoMatchingCredentialsException : AuthException()

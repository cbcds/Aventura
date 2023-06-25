package com.cbcds.aventura.core.common.exception

sealed class AuthException : AventuraException()

object EmailAlreadyInUseException : AuthException()

object UserDisabledException : AuthException()

object UserNotFoundException : AuthException()

object InvalidPasswordException : AuthException()

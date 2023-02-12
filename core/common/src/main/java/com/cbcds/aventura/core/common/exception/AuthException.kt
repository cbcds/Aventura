package com.cbcds.aventura.core.common.exception

sealed class AuthException : Exception()

class EmailAlreadyInUseException : AuthException()

class UserDisabledException : AuthException()

class UserNotFoundException : AuthException()

class InvalidPasswordException : AuthException()

class UnknownException : AuthException()
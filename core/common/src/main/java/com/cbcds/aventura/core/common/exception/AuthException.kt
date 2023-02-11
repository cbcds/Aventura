package com.cbcds.aventura.core.common.exception

sealed class AuthException : Exception()

class EmailAlreadyInUseException : AuthException()

class UnknownException : AuthException()
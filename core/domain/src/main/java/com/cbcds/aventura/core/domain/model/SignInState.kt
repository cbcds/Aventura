package com.cbcds.aventura.core.domain.model

sealed interface SignInState {

    object Success : SignInState

    data class Error(val cause: Throwable) : SignInState
}
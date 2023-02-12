package com.cbcds.aventura.core.domain.model

sealed interface SignUpState {

    object Success : SignUpState

    data class Error(val cause: Throwable) : SignUpState
}
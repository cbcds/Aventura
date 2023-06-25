package com.cbcds.aventura.core.domain.model

sealed interface SsoState {

    object Success : SsoState

    data class Error(val cause: Throwable) : SsoState
}
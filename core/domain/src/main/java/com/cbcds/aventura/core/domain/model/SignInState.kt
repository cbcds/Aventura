package com.cbcds.aventura.core.domain.model

sealed interface SignInState {

    object Success : SignInState

    object Error : SignInState
}
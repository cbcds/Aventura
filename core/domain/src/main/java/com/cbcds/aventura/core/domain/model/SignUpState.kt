package com.cbcds.aventura.core.domain.model

sealed interface SignUpState {

    object Success : SignUpState

    object Error : SignUpState
}
package com.cbcds.aventura.core.domain.model

sealed interface SignInDataValidationResult {

    object Success : SignInDataValidationResult

    data class Error(
        val emailError: EmailValidationError?,
        val passwordError: PasswordValidationError?,
    ) : SignInDataValidationResult
}

sealed interface SignUpDataValidationResult {

    object Success : SignUpDataValidationResult

    data class Error(
        val usernameError: UsernameValidationError?,
        val emailError: EmailValidationError?,
        val passwordError: PasswordValidationError?,
    ) : SignUpDataValidationResult
}

enum class UsernameValidationError {
    BLANK, TOO_SHORT, HAS_INVALID_CHARS
}

enum class PasswordValidationError {
    BLANK, TOO_SHORT, HAS_INVALID_CHARS
}

enum class EmailValidationError {
    BLANK, NOT_MATCHES_PATTERN
}

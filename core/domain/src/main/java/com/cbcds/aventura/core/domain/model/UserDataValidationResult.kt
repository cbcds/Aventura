package com.cbcds.aventura.core.domain.model

sealed interface UsernameValidationResult {

    object Success : UsernameValidationResult

    data class Error(val errors: Set<UsernameError>) : UsernameValidationResult

    enum class UsernameError {
        BLANK, TOO_SHORT, HAS_INVALID_CHARS
    }
}

sealed interface EmailValidationResult {

    object Success : EmailValidationResult

    data class Error(val errors: Set<EmailError>) : EmailValidationResult

    enum class EmailError {
        BLANK, NOT_MATCHES_PATTERN
    }
}

sealed interface PasswordValidationResult {

    object Success : PasswordValidationResult

    data class Error(val errors: Set<PasswordError>) : PasswordValidationResult

    enum class PasswordError {
        BLANK, TOO_SHORT, HAS_INVALID_CHARS
    }
}

sealed interface SignInDataValidationResult {

    object Success : SignInDataValidationResult

    data class Error(
        val emailError: EmailValidationResult.EmailError?,
        val passwordError: PasswordValidationResult.PasswordError?,
    ) : SignInDataValidationResult
}

sealed interface SignUpDataValidationResult {

    object Success : SignUpDataValidationResult

    data class Error(
        val usernameError: UsernameValidationResult.UsernameError?,
        val emailError: EmailValidationResult.EmailError?,
        val passwordError: PasswordValidationResult.PasswordError?,
    ) : SignUpDataValidationResult
}
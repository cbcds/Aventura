package com.cbcds.aventura.core.domain

import android.util.Patterns
import com.cbcds.aventura.core.domain.model.EmailValidationError
import com.cbcds.aventura.core.domain.model.PasswordValidationError
import com.cbcds.aventura.core.domain.model.SignInDataValidationResult
import com.cbcds.aventura.core.domain.model.SignUpDataValidationResult
import com.cbcds.aventura.core.domain.model.UsernameValidationError
import javax.inject.Inject

class ValidateUserDataInteractor @Inject constructor() {

    fun validateSignInData(email: String, password: String): SignInDataValidationResult {
        val emailValidationErrors = validateEmail(email)
        val passwordValidationErrors = validatePassword(password)
        return if (emailValidationErrors.isEmpty() && passwordValidationErrors.isEmpty()) {
            SignInDataValidationResult.Success
        } else {
            SignInDataValidationResult.Error(
                emailError = emailValidationErrors.getMainError(),
                passwordError = passwordValidationErrors.getMainError(),
            )
        }
    }

    fun validateSignUpData(
        username: String,
        email: String,
        password: String
    ): SignUpDataValidationResult {
        val usernameValidationErrors = validateUsername(username)
        val emailValidationErrors = validateEmail(email)
        val passwordValidationErrors = validatePassword(password)
        return if (usernameValidationErrors.isEmpty() &&
            emailValidationErrors.isEmpty() &&
            passwordValidationErrors.isEmpty()
        ) {
            SignUpDataValidationResult.Success
        } else {
            SignUpDataValidationResult.Error(
                usernameError = usernameValidationErrors.getMainError(),
                emailError = emailValidationErrors.getMainError(),
                passwordError = passwordValidationErrors.getMainError(),
            )
        }
    }

    private fun validateUsername(username: String): Set<UsernameValidationError> {
        val errors = mutableSetOf<UsernameValidationError>()
        if (username.isBlank()) {
            errors += UsernameValidationError.BLANK
        }
        if (username.length < USERNAME_MIN_LENGTH) {
            errors += UsernameValidationError.TOO_SHORT
        }
        if (!username.matches(USERNAME_PATTERN.toRegex())) {
            errors += UsernameValidationError.HAS_INVALID_CHARS
        }
        return errors
    }

    private fun validateEmail(username: String): Set<EmailValidationError> {
        val errors = mutableSetOf<EmailValidationError>()
        if (username.isBlank()) {
            errors += EmailValidationError.BLANK
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
            errors += EmailValidationError.NOT_MATCHES_PATTERN
        }
        return errors
    }

    private fun validatePassword(password: String): Set<PasswordValidationError> {
        val errors = mutableSetOf<PasswordValidationError>()
        if (password.isBlank()) {
            errors += PasswordValidationError.BLANK
        }
        if (password.length < PASSWORD_MIN_LENGTH) {
            errors += PasswordValidationError.TOO_SHORT
        }
        if (!password.matches(PASSWORD_PATTERN.toRegex())) {
            errors += PasswordValidationError.HAS_INVALID_CHARS
        }
        return errors
    }

    private fun Set<UsernameValidationError>.getMainError() =
        UsernameValidationError.BLANK.takeIf { it in this } ?: let {
            UsernameValidationError.TOO_SHORT.takeIf { it in this } ?: let {
                UsernameValidationError.HAS_INVALID_CHARS.takeIf { it in this }
            }
        }

    private fun Set<EmailValidationError>.getMainError() =
        EmailValidationError.BLANK.takeIf { it in this }
            ?: EmailValidationError.NOT_MATCHES_PATTERN.takeIf { it in this }

    private fun Set<PasswordValidationError>.getMainError() =
        PasswordValidationError.BLANK.takeIf { it in this } ?: let {
            PasswordValidationError.TOO_SHORT.takeIf { it in this } ?: let {
                PasswordValidationError.HAS_INVALID_CHARS.takeIf { it in this }
            }
        }

    companion object {

        private const val USERNAME_PATTERN = "^([0-9a-zA-Z._]){4,}$"
        private const val USERNAME_MIN_LENGTH = 4

        private const val PASSWORD_PATTERN = "^([0-9a-zA-Z@#\$%^&+=_]){8,}\$"
        private const val PASSWORD_MIN_LENGTH = 8
    }
}

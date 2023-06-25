package com.cbcds.aventura.core.domain

import android.util.Patterns
import com.cbcds.aventura.core.domain.model.EmailValidationResult
import com.cbcds.aventura.core.domain.model.PasswordValidationResult
import com.cbcds.aventura.core.domain.model.SignInDataValidationResult
import com.cbcds.aventura.core.domain.model.SignUpDataValidationResult
import com.cbcds.aventura.core.domain.model.UsernameValidationResult
import javax.inject.Inject

class ValidateUserDataUseCase @Inject constructor() {

    fun validateSignInData(email: String, password: String): SignInDataValidationResult {
        val emailValidationErrors = validateEmail(email) as? EmailValidationResult.Error
        val passwordValidationErrors = validatePassword(password) as? PasswordValidationResult.Error
        return if (emailValidationErrors == null && passwordValidationErrors == null) {
            SignInDataValidationResult.Success
        } else {
            SignInDataValidationResult.Error(
                emailError = emailValidationErrors?.getMainError(),
                passwordError = passwordValidationErrors?.getMainError(),
            )
        }
    }

    fun validateSignUpData(
        username: String,
        email: String,
        password: String
    ): SignUpDataValidationResult {
        val usernameValidationErrors = validateUsername(username) as? UsernameValidationResult.Error
        val emailValidationErrors = validateEmail(email) as? EmailValidationResult.Error
        val passwordValidationErrors = validatePassword(password) as? PasswordValidationResult.Error
        return if (usernameValidationErrors == null &&
            emailValidationErrors == null &&
            passwordValidationErrors == null
        ) {
            SignUpDataValidationResult.Success
        } else {
            SignUpDataValidationResult.Error(
                usernameError = usernameValidationErrors?.getMainError(),
                emailError = emailValidationErrors?.getMainError(),
                passwordError = passwordValidationErrors?.getMainError(),
            )
        }
    }

    private fun validateUsername(username: String): UsernameValidationResult {
        val errors = mutableSetOf<UsernameValidationResult.UsernameError>()
        if (username.isBlank()) {
            errors += UsernameValidationResult.UsernameError.BLANK
        }
        if (username.length < USERNAME_MIN_LENGTH) {
            errors += UsernameValidationResult.UsernameError.TOO_SHORT
        }
        if (!username.matches(USERNAME_PATTERN.toRegex())) {
            errors += UsernameValidationResult.UsernameError.HAS_INVALID_CHARS
        }
        return if (errors.isEmpty()) {
            UsernameValidationResult.Success
        } else {
            UsernameValidationResult.Error(errors)
        }
    }

    private fun validateEmail(username: String): EmailValidationResult {
        val errors = mutableSetOf<EmailValidationResult.EmailError>()
        if (username.isBlank()) {
            errors += EmailValidationResult.EmailError.BLANK
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
            errors += EmailValidationResult.EmailError.NOT_MATCHES_PATTERN
        }
        return if (errors.isEmpty()) {
            EmailValidationResult.Success
        } else {
            EmailValidationResult.Error(errors)
        }
    }

    private fun validatePassword(password: String): PasswordValidationResult {
        val errors = mutableSetOf<PasswordValidationResult.PasswordError>()
        if (password.isBlank()) {
            errors += PasswordValidationResult.PasswordError.BLANK
        }
        if (password.length < PASSWORD_MIN_LENGTH) {
            errors += PasswordValidationResult.PasswordError.TOO_SHORT
        }
        if (!password.matches(PASSWORD_PATTERN.toRegex())) {
            errors += PasswordValidationResult.PasswordError.HAS_INVALID_CHARS
        }
        return if (errors.isEmpty()) {
            PasswordValidationResult.Success
        } else {
            PasswordValidationResult.Error(errors)
        }
    }

    private fun UsernameValidationResult.Error.getMainError() =
        UsernameValidationResult.UsernameError.BLANK.takeIf { it in errors } ?: let {
            UsernameValidationResult.UsernameError.TOO_SHORT.takeIf { it in errors }
                ?: UsernameValidationResult.UsernameError.HAS_INVALID_CHARS
        }

    private fun EmailValidationResult.Error.getMainError() =
        EmailValidationResult.EmailError.BLANK.takeIf { it in errors }
            ?: EmailValidationResult.EmailError.NOT_MATCHES_PATTERN

    private fun PasswordValidationResult.Error.getMainError() =
        PasswordValidationResult.PasswordError.BLANK.takeIf { it in errors } ?: let {
            PasswordValidationResult.PasswordError.TOO_SHORT.takeIf { it in errors }
                ?: PasswordValidationResult.PasswordError.HAS_INVALID_CHARS
        }

    companion object {

        private const val USERNAME_PATTERN = "^([0-9a-zA-Z._]){4,}$"
        private const val USERNAME_MIN_LENGTH = 4

        private const val PASSWORD_PATTERN = "^([0-9a-zA-Z@#\$%^&+=_]){8,}\$"
        private const val PASSWORD_MIN_LENGTH = 8
    }
}
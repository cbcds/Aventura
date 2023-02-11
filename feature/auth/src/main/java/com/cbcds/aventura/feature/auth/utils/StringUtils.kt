package com.cbcds.aventura.feature.auth.utils

import androidx.annotation.StringRes
import com.cbcds.aventura.core.common.exception.EmailAlreadyInUseException
import com.cbcds.aventura.core.domain.model.EmailValidationResult
import com.cbcds.aventura.core.domain.model.PasswordValidationResult
import com.cbcds.aventura.core.domain.model.UsernameValidationResult
import com.cbcds.aventura.feature.auth.R
import com.cbcds.aventura.core.ui.R as coreR

@StringRes
fun UsernameValidationResult.UsernameError.toErrorStringId(): Int {
    return when (this) {
        UsernameValidationResult.UsernameError.BLANK -> R.string.empty_username_error
        UsernameValidationResult.UsernameError.TOO_SHORT -> R.string.username_too_short_error
        UsernameValidationResult.UsernameError.HAS_INVALID_CHARS -> R.string.username_invalid_chars_error
    }
}

@StringRes
fun EmailValidationResult.EmailError.toErrorStringId(): Int {
    return when (this) {
        EmailValidationResult.EmailError.BLANK -> R.string.empty_email_error
        EmailValidationResult.EmailError.NOT_MATCHES_PATTERN -> R.string.invalid_email_error
    }
}

@StringRes
fun PasswordValidationResult.PasswordError.toErrorStringId(): Int {
    return when (this) {
        PasswordValidationResult.PasswordError.BLANK -> R.string.empty_password_error
        PasswordValidationResult.PasswordError.TOO_SHORT -> R.string.password_too_short_error
        PasswordValidationResult.PasswordError.HAS_INVALID_CHARS -> R.string.password_invalid_chars_error
    }
}

fun Throwable.toErrorStringId(): Int {
    return when (this) {
        is EmailAlreadyInUseException -> R.string.email_exists_error
        else -> coreR.string.unknown_error
    }
}
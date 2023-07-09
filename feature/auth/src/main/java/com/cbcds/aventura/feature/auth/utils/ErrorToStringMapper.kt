package com.cbcds.aventura.feature.auth.utils

import androidx.annotation.StringRes
import com.cbcds.aventura.core.common.exception.EmailAlreadyInUseException
import com.cbcds.aventura.core.common.exception.InvalidPasswordException
import com.cbcds.aventura.core.common.exception.NoMatchingCredentialsException
import com.cbcds.aventura.core.common.exception.UserNotFoundException
import com.cbcds.aventura.core.domain.model.EmailValidationError
import com.cbcds.aventura.core.domain.model.PasswordValidationError
import com.cbcds.aventura.core.domain.model.UsernameValidationError
import com.cbcds.aventura.feature.auth.R
import com.cbcds.aventura.core.ui.R as coreR

@StringRes
internal fun UsernameValidationError.toErrorStringId(): Int {
    return when (this) {
        UsernameValidationError.BLANK -> R.string.empty_username_error
        UsernameValidationError.TOO_SHORT -> R.string.username_too_short_error
        UsernameValidationError.HAS_INVALID_CHARS -> R.string.username_invalid_chars_error
    }
}

@StringRes
internal fun EmailValidationError.toErrorStringId(): Int {
    return when (this) {
        EmailValidationError.BLANK -> R.string.empty_email_error
        EmailValidationError.NOT_MATCHES_PATTERN -> R.string.invalid_email_error
    }
}

@StringRes
internal fun PasswordValidationError.toErrorStringId(): Int {
    return when (this) {
        PasswordValidationError.BLANK -> R.string.empty_password_error
        PasswordValidationError.TOO_SHORT -> R.string.password_too_short_error
        PasswordValidationError.HAS_INVALID_CHARS -> R.string.password_invalid_chars_error
    }
}

@StringRes
internal fun Throwable.toErrorStringId(): Int {
    return when (this) {
        is EmailAlreadyInUseException -> R.string.email_exists_error
        is UserNotFoundException -> R.string.user_not_found_error
        is InvalidPasswordException -> R.string.incorrect_password_error
        is NoMatchingCredentialsException -> R.string.no_matching_credentials_error
        else -> coreR.string.unknown_error
    }
}

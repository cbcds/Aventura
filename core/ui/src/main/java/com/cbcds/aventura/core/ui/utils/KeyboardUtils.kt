package com.cbcds.aventura.core.ui.utils

import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions

fun (KeyboardActionScope.() -> Unit)?.toKeyboardActions(): KeyboardActions {
    return this?.let { KeyboardActions(onAny = this) } ?: KeyboardActions.Default
}
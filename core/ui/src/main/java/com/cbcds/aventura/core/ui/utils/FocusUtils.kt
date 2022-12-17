package com.cbcds.aventura.core.ui.utils

import androidx.compose.ui.focus.FocusManager

typealias OnClickListener = () -> Unit

fun OnClickListener.withClearFocus(focusManager: FocusManager): OnClickListener {
    return {
        focusManager.clearFocus()
        this()
    }
}
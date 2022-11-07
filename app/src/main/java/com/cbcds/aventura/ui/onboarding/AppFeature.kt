package com.cbcds.aventura.ui.onboarding

import androidx.annotation.DrawableRes

data class AppFeature(
    @DrawableRes
    val imageResId: Int,
    val title: String,
    val subtitle: String
)
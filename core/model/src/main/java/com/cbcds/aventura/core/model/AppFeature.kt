package com.cbcds.aventura.core.model

import androidx.annotation.DrawableRes

data class AppFeature(
    @DrawableRes
    val imageResId: Int,
    val title: String,
    val subtitle: String
)
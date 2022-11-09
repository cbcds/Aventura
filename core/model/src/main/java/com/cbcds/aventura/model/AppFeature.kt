package com.cbcds.aventura.model

import androidx.annotation.DrawableRes

data class AppFeature(
    @DrawableRes
    val imageResId: Int,
    val title: String,
    val subtitle: String
)
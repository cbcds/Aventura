package com.cbcds.aventura.core.data.api

import com.cbcds.aventura.core.model.AppFeature

interface OnboardingRepository {

    fun getAppFeatures(): List<AppFeature>
}
package com.cbcds.aventura.core.data.repository

import com.cbcds.aventura.core.model.AppFeature

interface OnboardingRepository {

    fun getAppFeatures(): List<AppFeature>
}
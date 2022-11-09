package com.cbcds.aventura.core.data.repository

import com.cbcds.aventura.model.AppFeature

interface OnboardingRepository {

    fun getAppFeatures(): List<AppFeature>
}
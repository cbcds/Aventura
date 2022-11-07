package com.cbcds.aventura.ui.onboarding

import android.content.Context
import com.cbcds.aventura.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class OnboardingRepository @Inject constructor(
    @ApplicationContext context: Context
) {

    private val appFeatures = listOf(
        AppFeature(
            imageResId = R.drawable.ic_onboarding_1,
            title = context.getString(R.string.onboarding_feature_title_1),
            subtitle = context.getString(R.string.onboarding_feature_subtitle_1)
        ),
        AppFeature(
            imageResId = R.drawable.ic_onboarding_2,
            title = context.getString(R.string.onboarding_feature_title_2),
            subtitle = context.getString(R.string.onboarding_feature_subtitle_2)
        ),
        AppFeature(
            imageResId = R.drawable.ic_onboarding_3,
            title = context.getString(R.string.onboarding_feature_title_3),
            subtitle = context.getString(R.string.onboarding_feature_subtitle_3)
        ),
        AppFeature(
            imageResId = R.drawable.ic_onboarding_4,
            title = context.getString(R.string.onboarding_feature_title_4),
            subtitle = context.getString(R.string.onboarding_feature_subtitle_4)
        ),
    )

    fun getAppFeatures() = appFeatures
}
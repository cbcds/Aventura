package com.cbcds.aventura.core.data.repository

import android.content.Context
import com.cbcds.aventura.core.data.R
import com.cbcds.aventura.core.model.AppFeature
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DefaultOnboardingRepository @Inject constructor(
    @ApplicationContext context: Context
) : OnboardingRepository {

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

    override fun getAppFeatures() = appFeatures
}
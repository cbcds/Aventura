package com.cbcds.aventura.feature.auth.onboarding

import androidx.lifecycle.ViewModel
import com.cbcds.aventura.core.data.repository.DefaultOnboardingRepository
import com.cbcds.aventura.core.data.repository.OnboardingRepository
import com.cbcds.aventura.model.AppFeature
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    onboardingRepository: OnboardingRepository
) : ViewModel() {

    val appFeatures: StateFlow<List<AppFeature>> =
        MutableStateFlow(onboardingRepository.getAppFeatures())
}
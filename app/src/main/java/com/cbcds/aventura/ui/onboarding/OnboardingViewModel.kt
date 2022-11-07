package com.cbcds.aventura.ui.onboarding

import androidx.lifecycle.ViewModel
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
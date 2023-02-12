package com.cbcds.aventura.feature.auth.onboarding

import androidx.lifecycle.ViewModel
import com.cbcds.aventura.core.data.api.OnboardingRepository
import com.cbcds.aventura.core.model.AppFeature
import com.cbcds.aventura.core.navigation.NavigationManager
import com.cbcds.aventura.feature.auth.navigation.SignInScreen
import com.cbcds.aventura.feature.auth.navigation.SignUpScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    onboardingRepository: OnboardingRepository,
    private val navigationManager: NavigationManager,
) : ViewModel() {

    val appFeatures: StateFlow<List<AppFeature>> =
        MutableStateFlow(onboardingRepository.getAppFeatures())

    fun toSignInScreen() {
        navigationManager.navigateAndClearStack(SignInScreen)
    }

    fun toSignUpScreen() {
        navigationManager.navigateAndClearStack(SignUpScreen)
    }
}
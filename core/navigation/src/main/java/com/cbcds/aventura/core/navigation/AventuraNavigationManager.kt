package com.cbcds.aventura.core.navigation

import com.cbcds.aventura.core.navigation.NavigationState.Idle
import com.cbcds.aventura.core.navigation.NavigationState.NavigateBack
import com.cbcds.aventura.core.navigation.NavigationState.NavigateToScreen
import com.cbcds.aventura.core.navigation.NavigationState.NavigateToScreenWithClearStack
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class AventuraNavigationManager @Inject constructor() : NavigationManager {

    private val _navigationState = MutableStateFlow<NavigationState>(Idle)
    override val navigationState: StateFlow<NavigationState>
        get() = _navigationState

    override fun navigateTo(screen: Screen) {
        _navigationState.value = NavigateToScreen(screen)
    }

    override fun navigateAndClearStack(screen: Screen) {
        _navigationState.value = NavigateToScreenWithClearStack(screen)
    }

    override fun navigateBack() {
        _navigationState.value = NavigateBack
    }

    override fun onNavigated() {
        _navigationState.value = Idle
    }

    /*override fun onNavigated(screen: Screen) {
        _navigationState.value = Idle(screen)
    }*/
}
package com.cbcds.aventura.core.navigation

import kotlinx.coroutines.flow.StateFlow

interface NavigationManager {

    val navigationState: StateFlow<NavigationState>

    fun navigateTo(screen: Screen)

    fun navigateAndClearStack(screen: Screen)

    fun navigateBack()

    fun onNavigated()
   // fun onNavigated(screen: Screen)
}
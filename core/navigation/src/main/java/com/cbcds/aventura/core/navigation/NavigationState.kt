package com.cbcds.aventura.core.navigation

sealed interface NavigationState {

    object Idle : NavigationState

    data class NavigateToScreen(val screen: Screen) : NavigationState

    data class NavigateToScreenWithClearStack(val screen: Screen) : NavigationState

    object NavigateBack : NavigationState
}
package com.cbcds.aventura.core.navigation

import kotlinx.coroutines.flow.MutableSharedFlow

sealed interface NavigationCommand {

    data class NavigateToScreen(val screen: Screen) : NavigationCommand

    data class NavigateToScreenAndClearStack(val screen: Screen) : NavigationCommand

    data class NavigateForResult(
        val screen: Screen,
        val resultFlow: MutableSharedFlow<Result<*>>,
    ) : NavigationCommand

    object NavigateBack : NavigationCommand

    data class FinishWithResult(val result: Result<*>): NavigationCommand
}

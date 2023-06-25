package com.cbcds.aventura.navigation

import androidx.navigation.NavHostController
import com.cbcds.aventura.core.navigation.Screen
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class Router(val navController: NavHostController) {

    private val screenStack = ArrayDeque<Screen>()
    private val _currentScreen = MutableStateFlow<Screen?>(null)
    val currentScreen: StateFlow<Screen?>
        get() = _currentScreen

    private val resultFlows = mutableMapOf<Screen, MutableSharedFlow<Result<*>>>()

    fun navigateTo(screen: Screen): Boolean {
        return runCatching {
            navController.navigate(screen.route) {
                launchSingleTop = true
            }
            screenStack.add(screen)
            updateCurrentScreenFlow()
        }.isSuccess
    }

    fun navigateWithClearStack(screen: Screen): Boolean {
        navController.backQueue.clear()
        screenStack.clear()
        resultFlows.clear()
        return navigateTo(screen).also { navigated ->
            if (!navigated) {
                updateCurrentScreenFlow()
            }
        }
    }

    fun navigateForResult(screen: Screen, resultFlow: MutableSharedFlow<Result<*>>): Boolean {
        return navigateTo(screen).also { navigated ->
            if (navigated) {
                resultFlows[screen] = resultFlow
            }
        }
    }

    fun navigateBack(): Boolean {
        return navController.popBackStack().also { navigated ->
            if (navigated) {
                screenStack.removeLast()
                resultFlows.remove(currentScreen.value)
                updateCurrentScreenFlow()
            }
        }
    }

    fun finishWithResult(result: Result<*>): Boolean {
        resultFlows[currentScreen.value]?.tryEmit(result)
        return navigateBack()
    }

    private fun updateCurrentScreenFlow() {
        _currentScreen.value = screenStack.lastOrNull()
    }
}

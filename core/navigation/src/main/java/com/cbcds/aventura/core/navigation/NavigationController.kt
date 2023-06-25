package com.cbcds.aventura.core.navigation

import kotlinx.coroutines.flow.Flow

interface NavigationController {

    val commands: Flow<NavigationCommand>

    fun navigate(screen: Screen)

    fun navigateAndClearStack(screen: Screen)

    fun navigateForResult(screen: Screen): Flow<Result<*>>

    fun navigateBack()

    fun finishWithResult(result: Result<*>)
}

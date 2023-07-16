package com.cbcds.aventura.core.navigation

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

class DefaultNavigationController @Inject constructor() : NavigationController {

    private val _commands = MutableSharedFlow<NavigationCommand>(
        extraBufferCapacity = COMMANDS_BUFFER_SIZE,
        onBufferOverflow = BufferOverflow.DROP_LATEST,
    )
    override val commands: Flow<NavigationCommand>
        get() = _commands.asSharedFlow()

    override fun navigate(screen: Screen) {
        _commands.tryEmit(NavigationCommand.NavigateToScreen(screen))
    }

    override fun navigateAndClearStack(screen: Screen) {
        _commands.tryEmit(NavigationCommand.NavigateToScreenAndClearStack(screen))
    }

    override fun navigateForResult(screen: Screen): Flow<Result<*>> {
        val resultFlow = MutableSharedFlow<Result<*>>(
            extraBufferCapacity = 1,
            onBufferOverflow = BufferOverflow.DROP_LATEST,
        )
        _commands.tryEmit(NavigationCommand.NavigateForResult(screen, resultFlow))
        return resultFlow
    }

    override fun navigateBack() {
        _commands.tryEmit(NavigationCommand.NavigateBack)
    }

    override fun finishWithResult(result: Result<*>) {
        _commands.tryEmit(NavigationCommand.FinishWithResult(result))
    }

    private companion object {

        const val COMMANDS_BUFFER_SIZE = 10
    }
}

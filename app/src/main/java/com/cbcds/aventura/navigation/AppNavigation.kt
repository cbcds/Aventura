package com.cbcds.aventura.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.cbcds.aventura.core.navigation.NavigationCommand
import com.cbcds.aventura.core.navigation.NavigationController
import com.cbcds.aventura.core.user.AuthStateManager
import com.cbcds.aventura.feature.auth.navigation.AuthFlow
import com.cbcds.aventura.ui.MainFlow

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun AppNavigation(
    navigationController: NavigationController,
    router: Router,
    modifier: Modifier = Modifier,
) {
    val navigationState: NavigationCommand? by
        navigationController.commands.collectAsStateWithLifecycle(null)
    LaunchedEffect(navigationState) {
        when (val command = navigationState) {
            is NavigationCommand.NavigateToScreen ->
                router.navigateTo(command.screen)
            is NavigationCommand.NavigateToScreenAndClearStack ->
                router.navigateWithClearStack(command.screen)
            is NavigationCommand.NavigateForResult ->
                router.navigateForResult(command.screen, command.resultFlow)
            is NavigationCommand.NavigateBack ->
                router.navigateBack()
            is NavigationCommand.FinishWithResult ->
                router.finishWithResult(command.result)
            else -> Unit
        }
    }

    val user by AuthStateManager.userFlow.collectAsStateWithLifecycle()
    NavHost(
        router = router,
        startScreenFlow = if (user != null) MainFlow else AuthFlow,
        modifier = modifier,
    )
}

@Composable
fun rememberRouter(navController: NavHostController = rememberNavController()): Router {
    return remember(navController) {
        Router(navController)
    }
}

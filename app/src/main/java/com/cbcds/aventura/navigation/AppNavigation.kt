package com.cbcds.aventura.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cbcds.aventura.core.navigation.NavigationManager
import com.cbcds.aventura.core.navigation.NavigationState
import com.cbcds.aventura.core.user.AuthStateManager
import com.cbcds.aventura.feature.auth.navigation.authGraph
import com.cbcds.aventura.feature.auth.navigation.OnboardingScreen
import com.cbcds.aventura.feature.auth.navigation.isAuthFlowScreen
import com.cbcds.aventura.ui.MainScreen

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun AppNavigation(
    navigationState: NavigationState,
    navigationManager: NavigationManager,
    router: Router,
) {
    LaunchedEffect(navigationState) {
        when (navigationState) {
            is NavigationState.NavigateToScreen ->
                router.navigateTo(navigationState.screen)
            is NavigationState.NavigateToScreenWithClearStack ->
                router.navigateWithClearStack(navigationState.screen)
            is NavigationState.NavigateBack ->
                router.navigateBack()
            else -> Unit
        }
        if (navigationState !is NavigationState.Idle) {
            navigationManager.onNavigated()
        }
    }

    val user by AuthStateManager.userFlow.collectAsStateWithLifecycle()
    if (user != null) {
        navigationManager.navigateTo(MainScreen)
    } else if (router.navController.currentDestination?.route?.isAuthFlowScreen() != true) {
        navigationManager.navigateTo(OnboardingScreen)
    }
}

fun NavGraphBuilder.appGraph() {
    authGraph()
    composable(MainScreen.route) {
        MainScreen()
    }
}

@Composable
fun rememberRouter(navController: NavHostController = rememberNavController()): Router {
    return remember(navController) {
        Router(navController)
    }
}
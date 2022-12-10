package com.cbcds.aventura.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.cbcds.aventura.core.navigation.NavigationManager
import com.cbcds.aventura.core.navigation.NavigationState
import com.cbcds.aventura.feature.auth.navigation.authGraph

@Composable
fun AppNavigation(
    navigationState: NavigationState,
    navigationManager: NavigationManager,
    router: Router
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
        if (navigationState != NavigationState.Idle) {
            navigationManager.onNavigated()
        }
    }
}

fun NavGraphBuilder.appGraph() {
    authGraph()
}

@Composable
fun rememberRouter(navController: NavHostController = rememberNavController()): Router {
    return remember(navController) {
        Router(navController)
    }
}
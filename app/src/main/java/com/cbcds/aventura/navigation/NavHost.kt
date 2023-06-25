package com.cbcds.aventura.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.cbcds.aventura.core.navigation.ScreenFlow
import com.cbcds.aventura.feature.auth.navigation.authGraph
import com.cbcds.aventura.ui.MainFlow
import com.cbcds.aventura.ui.MainScreen

@Composable
fun NavHost(
    router: Router,
    startScreenFlow: ScreenFlow,
    modifier: Modifier = Modifier,
) {
    androidx.navigation.compose.NavHost(
        navController = router.navController,
        startDestination = startScreenFlow.route,
        modifier = modifier,
    ) {
        appGraph()
    }
}

fun NavGraphBuilder.appGraph() {
    authGraph()
    navigation(
        route = MainFlow.route,
        startDestination = MainScreen.route,
    ) {
        composable(MainScreen.route) {
            MainScreen()
        }
    }
}

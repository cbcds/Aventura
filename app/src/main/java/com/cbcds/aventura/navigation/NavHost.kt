package com.cbcds.aventura.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cbcds.aventura.core.navigation.Screen

@Composable
fun NavHost(
    router: Router,
    startScreen: Screen,
    modifier: Modifier = Modifier,
) {
    androidx.navigation.compose.NavHost(
        navController = router.navController,
        startDestination = startScreen.route,
        modifier = modifier,
    ) {
        appGraph()
    }
}
package com.cbcds.aventura.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.cbcds.aventura.core.navigation.ScreenFlow

@Composable
fun NavHost(
    navController: NavHostController,
    startScreenFlow: ScreenFlow,
    modifier: Modifier = Modifier,
) {
    androidx.navigation.compose.NavHost(
        navController = navController,
        startDestination = startScreenFlow.route,
        modifier = modifier,
    ) {
        appGraph()
    }
}
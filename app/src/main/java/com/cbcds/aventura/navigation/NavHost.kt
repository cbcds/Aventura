package com.cbcds.aventura.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.cbcds.aventura.core.navigation.Screen

@Composable
fun NavHost(
    navController: NavHostController,
    onNavigateToScreen: (Screen) -> Unit,
    onBackClick: () -> Unit,
    startScreen: Screen,
    modifier: Modifier = Modifier,
) {
    androidx.navigation.compose.NavHost(
        navController = navController,
        startDestination = startScreen.name,
        modifier = modifier,
    ) {
        composable(route = startScreen.name) {

        }
    }
}
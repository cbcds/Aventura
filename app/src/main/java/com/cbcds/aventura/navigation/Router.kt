package com.cbcds.aventura.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.cbcds.aventura.core.navigation.Screen

@Stable
class Router(val navController: NavHostController) {

    fun navigate(screen: Screen) {
        navController.navigate(screen.route)
    }

    fun onBackClick() {
        navController.popBackStack()
    }
}

@Composable
fun rememberRouter(navController: NavHostController = rememberNavController()): Router {
    return remember(navController) {
        Router(navController)
    }
}

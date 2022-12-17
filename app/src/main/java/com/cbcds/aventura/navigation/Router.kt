package com.cbcds.aventura.navigation

import androidx.navigation.NavHostController
import com.cbcds.aventura.core.navigation.Screen

class Router(val navController: NavHostController) {

    fun navigateTo(screen: Screen) {
        navController.navigate(screen.route) {
            launchSingleTop = true
        }
    }

    fun navigateWithClearStack(screen: Screen) {
        navController.backQueue.clear()
        navController.navigate(screen.route)
    }

    fun navigateBack(): Boolean {
        return navController.popBackStack()
    }
}

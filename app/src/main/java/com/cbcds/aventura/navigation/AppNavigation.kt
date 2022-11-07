package com.cbcds.aventura.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.cbcds.aventura.core.navigation.Screen
import com.cbcds.aventura.ui.onboarding.OnboardingScreen

object OnboardingScreen : Screen {

    override val route: String = "onboarding"
}

fun NavGraphBuilder.appGraph() {
    composable(route = OnboardingScreen.route) {
        OnboardingScreen()
    }
}
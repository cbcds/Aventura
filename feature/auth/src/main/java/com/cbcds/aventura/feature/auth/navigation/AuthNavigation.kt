package com.cbcds.aventura.feature.auth.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.cbcds.aventura.core.navigation.Screen
import com.cbcds.aventura.core.navigation.ScreenFlow
import com.cbcds.aventura.feature.auth.onboarding.OnboardingScreen
import com.cbcds.aventura.feature.auth.signin.SignInScreen
import com.cbcds.aventura.feature.auth.signup.SignUpScreen

object AuthFlow : ScreenFlow {

    override val route: String = "authenticationFlow"
}

object OnboardingScreen : Screen {

    override val route: String = "onboarding"
}

object SignInScreen : Screen {

    override val route: String = "signIn"
}

object SignUpScreen : Screen {

    override val route: String = "signUp"
}

fun NavGraphBuilder.authGraph() {
    navigation(
        startDestination = OnboardingScreen.route,
        route = AuthFlow.route
    ) {
        composable(route = OnboardingScreen.route) {
            OnboardingScreen()
        }
        composable(route = SignInScreen.route) {
            SignInScreen()
        }
        composable(route = SignUpScreen.route) {
            SignUpScreen()
        }
    }
}
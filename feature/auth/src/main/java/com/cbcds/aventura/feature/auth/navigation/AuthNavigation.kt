package com.cbcds.aventura.feature.auth.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.navigation
import com.cbcds.aventura.feature.auth.onboarding.OnboardingScreen
import com.cbcds.aventura.feature.auth.signin.SignInScreen
import com.cbcds.aventura.feature.auth.signup.SignUpScreen
import com.cbcds.aventura.feature.auth.sso.FacebookAuthScreen
import com.cbcds.aventura.feature.auth.sso.GithubAuthScreen
import com.cbcds.aventura.feature.auth.sso.GoogleAuthScreen

fun NavGraphBuilder.authGraph() {
    navigation(
        route = AuthFlow.route,
        startDestination = OnboardingScreen.route,
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
        dialog(route = GoogleSsoScreen.route) {
            GoogleAuthScreen()
        }
        dialog(route = FacebookSsoScreen.route) {
            FacebookAuthScreen()
        }
        dialog(route = GithubSsoScreen.route) {
            GithubAuthScreen()
        }
    }
}

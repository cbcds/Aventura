package com.cbcds.aventura.feature.auth.navigation

import com.cbcds.aventura.core.navigation.Screen
import com.cbcds.aventura.core.navigation.ScreenFlow

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

object GoogleSsoScreen : Screen {

    override val route: String = "googleSso"
}

object FacebookSsoScreen : Screen {

    override val route: String = "facebookSso"
}

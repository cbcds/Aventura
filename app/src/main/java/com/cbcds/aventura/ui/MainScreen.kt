package com.cbcds.aventura.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cbcds.aventura.core.navigation.Screen
import com.cbcds.aventura.core.user.AuthStateManager

object MainScreen : Screen {

    override val route: String = "mainScreen"
}

@Composable
fun MainScreen() {
    val text = AuthStateManager.user.let {
        "Name: ${it?.name}\nEmail: ${it?.email}"
    }
    Box(Modifier.fillMaxSize()) {
        Text(text = text)
    }
}
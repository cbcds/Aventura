package com.cbcds.aventura.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.cbcds.aventura.core.navigation.Screen
import com.cbcds.aventura.core.user.AuthStateManager

object MainScreen : Screen {

    override val route: String = "mainScreen"
}

@Composable
internal fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    MainScreen(onSignOutClick = viewModel::signOut)
}

@Composable
fun MainScreen(onSignOutClick: () -> Unit) {
    val text = AuthStateManager.user.let {
        "Name: ${it?.name}\nEmail: ${it?.email}"
    }
    Column {
        Box {
            Text(text = text)
        }
        Button(onClick = onSignOutClick) {
            Text(text = "Sign out")
        }
    }
}
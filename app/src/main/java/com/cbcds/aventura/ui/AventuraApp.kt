package com.cbcds.aventura.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.cbcds.aventura.core.ui.component.Background
import com.cbcds.aventura.core.ui.theme.AventuraAppTheme
import com.cbcds.aventura.navigation.NavHost
import com.cbcds.aventura.navigation.OnboardingScreen
import com.cbcds.aventura.navigation.Router
import com.cbcds.aventura.navigation.rememberRouter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AventuraApp(router: Router = rememberRouter()) {
    AventuraAppTheme {
        Background {
            Scaffold(
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.onSurface
            ) { padding ->
                Row(
                    Modifier
                        .fillMaxSize()
                        .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal))
                ) {
                    NavHost(
                        router = router,
                        startScreen = OnboardingScreen,
                        modifier = Modifier.padding(padding)
                    )
                }
            }
        }
    }
}
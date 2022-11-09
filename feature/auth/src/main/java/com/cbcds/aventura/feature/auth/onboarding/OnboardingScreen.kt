package com.cbcds.aventura.feature.auth.onboarding

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cbcds.aventura.feature.auth.R
import androidx.hilt.navigation.compose.hiltViewModel
import com.cbcds.aventura.core.ui.component.FilledTextButton
import com.cbcds.aventura.core.ui.component.TextButton
import com.cbcds.aventura.model.AppFeature

@Composable
fun OnboardingScreen(
    viewModel: OnboardingViewModel = hiltViewModel()
) {
    val appFeatures by viewModel.appFeatures.collectAsState()
    OnboardingScreen(appFeatures)
}

@Composable
fun OnboardingScreen(appFeatures: List<AppFeature>) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OnboardingPager(appFeatures, modifier = Modifier.padding(horizontal = 20.dp))

        FilledTextButton(
            modifier = Modifier
                .width(200.dp)
                .padding(start = 20.dp, top = 35.dp, end = 20.dp),
            onClick = {
                // TODO
            }) {
            Text(stringResource(R.string.login))
        }

        Row(modifier = Modifier.padding(start = 20.dp, top = 24.dp, end = 20.dp)) {
            Text(
                text = stringResource(R.string.onboarding_register_question),
                style = MaterialTheme.typography.bodyMedium
            )
            TextButton(onClick = {
                // TODO
            }) {
                Text(
                    text = stringResource(R.string.register),
                    modifier = Modifier.padding(start = 4.dp),
                    textDecoration = TextDecoration.Underline,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
                )
            }
        }
    }
}

@Preview
@Composable
fun OnboardingScreenPreview() {
    OnboardingScreen()
}
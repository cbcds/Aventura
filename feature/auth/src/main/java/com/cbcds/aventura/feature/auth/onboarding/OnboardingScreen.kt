package com.cbcds.aventura.feature.auth.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cbcds.aventura.core.model.AppFeature
import com.cbcds.aventura.core.ui.component.base.FilledTextButton
import com.cbcds.aventura.core.ui.component.base.TextButton
import com.cbcds.aventura.feature.auth.R

@Composable
internal fun OnboardingScreen(
    viewModel: OnboardingViewModel = hiltViewModel()
) {
    val appFeatures by viewModel.appFeatures.collectAsStateWithLifecycle()
    OnboardingScreen(
        appFeatures = appFeatures,
        onSignInButtonClick = viewModel::toSignInScreen,
        onSignUpButtonClick = viewModel::toSignUpScreen,
    )
}

@Composable
private fun OnboardingScreen(
    appFeatures: List<AppFeature>,
    onSignInButtonClick: () -> Unit,
    onSignUpButtonClick: () -> Unit,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = scrollState),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OnboardingPager(appFeatures, modifier = Modifier.padding(horizontal = 20.dp))

        FilledTextButton(
            modifier = Modifier
                .width(200.dp)
                .padding(start = 20.dp, top = 35.dp, end = 20.dp),
            onClick = onSignInButtonClick
        ) {
            Text(stringResource(R.string.sign_in))
        }

        Row(modifier = Modifier.padding(start = 20.dp, top = 24.dp, end = 20.dp)) {
            Text(
                text = stringResource(R.string.sign_up_question),
                style = MaterialTheme.typography.bodyMedium
            )
            TextButton(onClick = onSignUpButtonClick) {
                Text(
                    text = stringResource(R.string.sign_up),
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
private fun OnboardingScreenPreview() {
    OnboardingScreen()
}

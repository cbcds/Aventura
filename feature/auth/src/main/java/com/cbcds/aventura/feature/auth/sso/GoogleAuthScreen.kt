package com.cbcds.aventura.feature.auth.sso

import android.app.Activity.RESULT_OK
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.android.gms.auth.api.identity.BeginSignInResult

@Composable
internal fun GoogleAuthScreen(
    viewModel: GoogleAuthViewModel = hiltViewModel()
) {
    val authUiState by viewModel.googleAuthUiState.collectAsStateWithLifecycle()
    when (val state = authUiState) {
        is GoogleAuthUiState.Initial ->
            Unit
        is GoogleAuthUiState.ReadyToShowAuth ->
            OneTapScreen(
                beginSignInResult = state.beginSignInResult,
                onAuthSuccess = viewModel::auth,
                onAuthFailure = viewModel::finish,
                onAuthCancel = viewModel::finish,
            )
    }
}

@Composable
private fun OneTapScreen(
    beginSignInResult: BeginSignInResult,
    onAuthSuccess: (Intent?) -> Unit,
    onAuthFailure: () -> Unit,
    onAuthCancel: () -> Unit,
) {
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            onAuthSuccess(result.data)
        } else {
            onAuthCancel()
        }
    }

    SideEffect {
        val intent = IntentSenderRequest.Builder(beginSignInResult.pendingIntent.intentSender)
            .build()
        launcher.launch(intent)
    }
}

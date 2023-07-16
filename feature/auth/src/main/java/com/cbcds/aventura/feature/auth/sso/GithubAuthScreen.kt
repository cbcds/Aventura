package com.cbcds.aventura.feature.auth.sso

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.cbcds.aventura.core.common.utils.activity
import com.google.firebase.auth.FirebaseAuth

@Composable
internal fun GithubAuthScreen(
    viewModel: GithubAuthViewModel = hiltViewModel(),
) {
    val pendingAuthTask by remember {
        mutableStateOf(FirebaseAuth.getInstance().pendingAuthResult)
    }

    pendingAuthTask
        ?.addOnSuccessListener(viewModel::auth)
        ?.addOnFailureListener(viewModel::finish)
        ?: run {
            LocalContext.current.activity()?.let { activity ->
                FirebaseAuth.getInstance()
                    .startActivityForSignInWithProvider(activity, viewModel.oAuthProvider)
                    .addOnSuccessListener(viewModel::auth)
                    .addOnFailureListener(viewModel::finish)
            }
    }
}

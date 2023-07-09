package com.cbcds.aventura.feature.auth.sso

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult

@Composable
internal fun FacebookAuthScreen(
    viewModel: FacebookAuthViewModel = hiltViewModel(),
) {
    val callbackManager by remember { mutableStateOf(CallbackManager.Factory.create()) }
    val callback by remember {
        mutableStateOf(
            object : FacebookCallback<LoginResult> {

                override fun onCancel() = viewModel.cancel()

                override fun onError(error: FacebookException) = viewModel.finish(error)

                override fun onSuccess(result: LoginResult) = viewModel.auth(result)
            }
        )
    }
    SideEffect {
        LoginManager.getInstance().registerCallback(callbackManager, callback)
    }

    val launcher = rememberLauncherForActivityResult(
        contract = LoginManager.getInstance().createLogInActivityResultContract(callbackManager),
        onResult = { /* result is processed in the callback */ }
    )
    SideEffect {
        launcher.launch(viewModel.permissions)
    }
}

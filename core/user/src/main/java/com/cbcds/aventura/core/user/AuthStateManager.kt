package com.cbcds.aventura.core.user

import com.cbcds.aventura.core.model.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object AuthStateManager {

    private val auth = Firebase.auth

    private var _userFlow = MutableStateFlow(auth.currentUser?.toUser())
    val userFlow: StateFlow<User?>
        get() = _userFlow

    val user: User?
        get() = userFlow.value

    init {
        auth.addAuthStateListener {
            _userFlow.value = auth.currentUser?.toUser()
        }
    }
}

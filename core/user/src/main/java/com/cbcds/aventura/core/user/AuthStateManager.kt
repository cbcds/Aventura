package com.cbcds.aventura.core.user

import com.cbcds.aventura.core.model.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

object AuthStateManager {

    private val auth = Firebase.auth

    private var firebaseUser = auth.currentUser
    private var _user = firebaseUser?.toUser()
    val user: User?
        get() {
            if (firebaseUser != auth.currentUser) {
                firebaseUser = auth.currentUser
                _user = firebaseUser?.toUser()
            }
            return _user
        }
}
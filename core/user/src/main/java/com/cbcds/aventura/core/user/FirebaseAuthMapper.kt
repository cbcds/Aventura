package com.cbcds.aventura.core.user

import com.cbcds.aventura.core.model.User
import com.google.firebase.auth.FirebaseUser

internal fun FirebaseUser.toUser(): User {
    return User(
        name = displayName,
        email = email,
    )
}

package com.cbcds.aventura.core.network.firebase

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

internal object FirebaseEmulator {

    private const val HOST = "localhost"
    private const val PORT = 9099

    fun enableAuth() {
        Firebase.auth.useEmulator(HOST, PORT)
    }
}

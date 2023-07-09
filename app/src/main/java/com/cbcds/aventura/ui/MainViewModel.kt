package com.cbcds.aventura.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cbcds.aventura.core.domain.SignOutInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val signOutInteractor: SignOutInteractor,
) : ViewModel() {

    fun signOut() {
        viewModelScope.launch {
            signOutInteractor.signOut()
        }
    }
}

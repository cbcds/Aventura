package com.cbcds.aventura.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cbcds.aventura.core.domain.SignOutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val signOutUseCase: SignOutUseCase,
) : ViewModel() {

    fun signOut() {
        viewModelScope.launch {
            signOutUseCase.signOut()
        }
    }
}
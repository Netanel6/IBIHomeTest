package com.netanel.ibihometest.login.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {
    private val _authStatus = MutableLiveData<AuthStatus>()
    val authStatus: LiveData<AuthStatus> get() = _authStatus

    fun notifyAuthenticationSuccess() {
        _authStatus.postValue(AuthStatus.Success)
    }

    fun notifyAuthenticationFailure(error: String) {
        _authStatus.postValue(AuthStatus.Failure(error))
    }
}

sealed class AuthStatus {
    data object Success : AuthStatus()
    data class Failure(val message: String) : AuthStatus()
    data object Loading : AuthStatus()
}
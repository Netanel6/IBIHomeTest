package com.netanel.ibihometest.login.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {
    private val _authStatus = MutableLiveData<AuthStatus>()
    val authStatus: LiveData<AuthStatus> get() = _authStatus

    fun notifyAuthenticationSuccess() {
        viewModelScope.launch {
            _authStatus.postValue(AuthStatus.Success)
            delay(1500)
        }
    }

    fun notifyAuthenticationFailure(error: String) {
        viewModelScope.launch {
            _authStatus.postValue(AuthStatus.Failure(error))
        }
    }
}

sealed class AuthStatus {
    data object Success : AuthStatus()
    data class Failure(val message: String) : AuthStatus()
    data object Loading : AuthStatus()
}
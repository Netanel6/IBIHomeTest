package com.netanel.ibihometest.login

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.netanel.ibihometest.databinding.ActivityLoginBinding
import com.netanel.ibihometest.login.ui.AuthStatus
import com.netanel.ibihometest.login.ui.LoginViewModel
import com.netanel.ibihometest.login.utils.AuthCallback
import com.netanel.ibihometest.login.utils.CombinedLoginManager
import com.netanel.ibihometest.login.utils.LoginManager
import com.netanel.ibihometest.login.utils.shouldShow
import com.netanel.ibihometest.login.utils.showTopSnackbar
import com.netanel.ibihometest.products.MainActivity
import com.netanel.ibihometest.utils.PrefsHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity(), AuthCallback {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    private var progressBar: ProgressBar? = null
    private var loginManager: LoginManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        progressBar = binding.progressBar
        setupListeners()
        observeAuthenticationState()
    }

    private fun setupListeners() {
        loginManager = CombinedLoginManager(this)
        binding.loginButton.setOnClickListener {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()
            loginManager?.authenticateUser(username, password, this)
        }

        binding.biometricLoginButton.setOnClickListener {
            authenticateBiometric()
        }
    }

    private fun authenticateBiometric() {
        loginManager?.authenticateBiometric(this)
    }

    private fun observeAuthenticationState() {
        viewModel.authStatus.observe(this, { status ->
            when (status) {
                is AuthStatus.Success -> navigateToHome()
                is AuthStatus.Failure -> showErrorMessage(status.message)
                is AuthStatus.Loading -> progressBar?.shouldShow(true)
                else -> {}
            }
        })
    }

    private fun navigateToHome() {
        progressBar?.shouldShow(false)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun showErrorMessage(message: String) {
        progressBar?.shouldShow(false)
        this.showTopSnackbar(message)
    }

    override fun onSuccess() {
        viewModel.notifyAuthenticationSuccess()
        PrefsHelper.saveBoolean(PrefsHelper.PREF_IS_LOGGED_IN, true)
    }

    override fun onFailure(error: String) {
        viewModel.notifyAuthenticationFailure(error)
    }
}

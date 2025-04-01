package com.netanel.ibihometest.login.utils

import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.netanel.ibihometest.R

interface LoginManager {
    fun authenticateUser(username: String, password: String, callback: AuthCallback)
    fun authenticateBiometric(
        callback: AuthCallback
    )
}

class CombinedLoginManager(private val activity: FragmentActivity) : LoginManager {
    override fun authenticateUser(username: String, password: String, callback: AuthCallback) {
        if (username == "user" && password == "password") {
            callback.onSuccess()
        } else {
            callback.onFailure(activity.getString(R.string.error_message))
        }
    }

    override fun authenticateBiometric(callback: AuthCallback) {
        val executor = ContextCompat.getMainExecutor(activity)
        val biometricPrompt =
            BiometricPrompt(activity, executor, object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    callback.onFailure(errString.toString())
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    callback.onSuccess()
                }

                override fun onAuthenticationFailed() {
                    callback.onFailure(activity.getString(R.string.bio_error_message))
                }
            })

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(activity.getString(R.string.bio_title))
            .setSubtitle(activity.getString(R.string.bio_subTitle))
            .setNegativeButtonText(activity.getString(R.string.bio_negative_button))
            .build()

        biometricPrompt.authenticate(promptInfo)
    }
}

interface AuthCallback {
    fun onSuccess()
    fun onFailure(error: String)
}

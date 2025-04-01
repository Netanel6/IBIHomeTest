package com.netanel.ibihometest

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.netanel.ibihometest.login.LoginActivity
import com.netanel.ibihometest.products.MainActivity
import com.netanel.ibihometest.utils.PrefsHelper
import com.netanel.ibihometest.utils.PrefsHelper.PREF_IS_LOGGED_IN


@SuppressLint("CustomSplashScreen")
class RoutingActivity : AppCompatActivity() {

    private var navigated = false

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)

        splashScreen.setKeepOnScreenCondition { true }
        if (!navigated) {
            routeUser()
        }
    }

    private fun routeUser() {
        val isLoggedIn = PrefsHelper.getBoolean(PREF_IS_LOGGED_IN, false)

        val targetActivity = if (isLoggedIn == true) {
            MainActivity::class.java
        } else {
            LoginActivity::class.java
        }

        val intent = Intent(this, targetActivity)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        startActivity(intent)

        finish()

        navigated = true
    }
}
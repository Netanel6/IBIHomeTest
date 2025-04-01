package com.netanel.ibihometest

import android.app.Application
import com.netanel.ibihometest.utils.PrefsHelper
import dagger.hilt.android.HiltAndroidApp


/**
 * Created by netanelamar on 01/04/2025.
 * NetanelCA2@gmail.com
 */

@HiltAndroidApp
class IBIApp: Application() {

    override fun onCreate() {
        super.onCreate()
        PrefsHelper.init(this)
    }
}
package com.netanel.ibihometest.products.ui.settings

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModel
import com.netanel.ibihometest.utils.LanguageHelper
import com.netanel.ibihometest.utils.PrefsHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor() : ViewModel() {

    fun isDarkTheme(): Boolean = PrefsHelper.isDarkMode()

    fun toggleTheme() {
        val current = PrefsHelper.isDarkMode()
        val newMode = !current
        PrefsHelper.setDarkMode(newMode)

        AppCompatDelegate.setDefaultNightMode(
            if (newMode) AppCompatDelegate.MODE_NIGHT_YES
            else AppCompatDelegate.MODE_NIGHT_NO
        )

    }

    fun toggleLanguage(context: Context) {
        val current = PrefsHelper.getLanguage()
        val newLang = if (current == "en") "he" else "en"

        PrefsHelper.setLanguage(newLang)
        LanguageHelper.setLocale(context, newLang)
    }

    fun logout() {
        PrefsHelper.clearAll()
    }
}

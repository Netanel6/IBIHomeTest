package com.netanel.ibihometest.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit // Import SharedPreferences KTX extension

object PrefsHelper {

    private const val PREFS_FILENAME = "com.netanel.hometest.prefs"
    private lateinit var sharedPreferences: SharedPreferences

    private var isInitialized = false

    const val PREF_IS_LOGGED_IN = "is_logged_in"
    const val DARK_MODE = "dark_mode"
    const val LANG = "lang"

    fun init(context: Context) {
        if (isInitialized) {
            Logger.warn("PrefsHelper", "PrefsHelper already initialized.")
             throw IllegalStateException("PrefsHelper has already been initialized.")
        }
        sharedPreferences = context.applicationContext.getSharedPreferences(
            PREFS_FILENAME,
            Context.MODE_PRIVATE
        )
        isInitialized = true
    }

    private fun checkInitialized() {
        if (!isInitialized) {
            throw IllegalStateException(
                "PrefsHelper must be initialized by calling init(context) first."
            )
        }
    }

    fun saveString(key: String, value: String?) {
        checkInitialized()
        sharedPreferences.edit { putString(key, value) }
    }

    fun getString(key: String, defaultValue: String? = null): String? {
        checkInitialized()
        return sharedPreferences.getString(key, defaultValue)
    }

    fun saveBoolean(key: String, value: Boolean) {
        checkInitialized()
        sharedPreferences.edit { putBoolean(key, value) }
    }

    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean? {
        checkInitialized()
        return sharedPreferences.getBoolean(key, defaultValue)
    }



    fun isDarkMode(): Boolean = sharedPreferences.getBoolean(DARK_MODE, false)

    fun setDarkMode(enabled: Boolean) {
        sharedPreferences.edit().putBoolean(DARK_MODE, enabled).apply()
    }

    fun setLanguage(lang: String) {
        sharedPreferences.edit().putString(LANG, lang).apply()
    }

    fun getLanguage(): String = sharedPreferences.getString(LANG, "en") ?: "en"

    fun remove(key: String) {
        checkInitialized()
        sharedPreferences.edit { remove(key) }
    }

    fun clearAll() {
        checkInitialized()
        sharedPreferences.edit { clear() }
    }

}
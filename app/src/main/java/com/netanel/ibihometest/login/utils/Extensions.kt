package com.netanel.ibihometest.login.utils

import android.app.Activity
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar


/**
 * Created by netanelamar on 01/04/2025.
 * NetanelCA2@gmail.com
 */

fun ProgressBar.shouldShow(value: Boolean){
    this.isVisible = value
}

fun Activity.showTopSnackbar(message: String) {
    val snackbar = Snackbar.make(
        this.findViewById(android.R.id.content),
        message,
        Snackbar.LENGTH_SHORT
    )

    val view = snackbar.view
    val params = view.layoutParams as FrameLayout.LayoutParams
    params.gravity = Gravity.TOP
    params.setMargins(0, 200, 0, 0)
    view.layoutParams = params

    snackbar.show()
}
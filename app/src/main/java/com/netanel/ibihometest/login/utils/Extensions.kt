package com.netanel.ibihometest.login.utils

import android.widget.ProgressBar
import androidx.core.view.isVisible

/**
 * Created by netanelamar on 01/04/2025.
 * NetanelCA2@gmail.com
 */

fun ProgressBar.shouldShow(value: Boolean){
    this.isVisible = value
}
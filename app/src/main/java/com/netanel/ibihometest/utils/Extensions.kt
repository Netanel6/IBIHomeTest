package com.netanel.ibihometest.utils

import android.app.Activity
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.core.view.isVisible
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.snackbar.Snackbar
import com.netanel.ibihometest.localdb.ProductEntity
import com.netanel.ibihometest.products.data.model.Product


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

fun LottieAnimationView.showLoading() {
    setAnimation("loading.json")
    visibility = android.view.View.VISIBLE
    playAnimation()
}

fun LottieAnimationView.showSuccess() {
    setAnimation("correct.json")
    visibility = android.view.View.VISIBLE
    playAnimation()
}

fun LottieAnimationView.showError() {
    setAnimation("wrong.json")
    visibility = android.view.View.VISIBLE
    playAnimation()
    postDelayed({
        hideAnimation()
    }, 2000)
}

fun LottieAnimationView.hideAnimation() {
    cancelAnimation()
    visibility = android.view.View.GONE
}

fun Product.toEntity() = ProductEntity(id, title, description, price, thumbnail)

fun ProductEntity.toProduct() = Product(id, title, description, price, thumbnail)
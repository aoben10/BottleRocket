package com.theobencode.victoroben.bottlerocket.presentation.extensions

import android.databinding.BindingAdapter
import android.support.v4.widget.SwipeRefreshLayout
import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.theobencode.victoroben.bottlerocket.R

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun SwipeRefreshLayout.startRefreshing() {
    isRefreshing = true
}

fun SwipeRefreshLayout.stopRefreshing() {
    isRefreshing = false
}

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String?) {
    Picasso.get().load(url).placeholder(R.drawable.placeholder).into(this)
}

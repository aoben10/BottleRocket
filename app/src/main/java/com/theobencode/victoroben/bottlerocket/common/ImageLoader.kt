package com.theobencode.victoroben.bottlerocket.common

import android.support.annotation.DrawableRes
import android.widget.ImageView

interface ImageLoader {
    fun load(url: String, imageView: ImageView, @DrawableRes placeholder: Int, callback: (Boolean) -> Unit)
}

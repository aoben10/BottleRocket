package com.theobencode.victoroben.bottlerocket.common

import android.support.annotation.DrawableRes
import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class PicassoImageLoader(private val picasso: Picasso) : ImageLoader {

    override fun load(url: String, imageView: ImageView, @DrawableRes placeholder: Int, callback: (Boolean) -> Unit) {
        picasso.load(url).placeholder(placeholder).into(imageView, FetchCallback(callback))
    }

    private class FetchCallback(val delegate: (Boolean) -> Unit) : Callback {
        override fun onSuccess() {
            delegate(true)
        }

        override fun onError(e: Exception?) {
            delegate(false)
        }

    }
}
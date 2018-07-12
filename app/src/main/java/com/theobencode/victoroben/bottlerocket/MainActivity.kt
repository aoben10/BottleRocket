package com.theobencode.victoroben.bottlerocket

import android.os.Bundle
import com.google.gson.Gson
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var gson: Gson

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

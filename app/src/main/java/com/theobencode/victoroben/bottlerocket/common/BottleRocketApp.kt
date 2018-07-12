package com.theobencode.victoroben.bottlerocket.common

import android.app.Activity
import android.app.Application
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.theobencode.victoroben.bottlerocket.common.Constants.Companion.APP_DEBUG
import com.theobencode.victoroben.bottlerocket.di.components.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class BottleRocketApp : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        initializeLogger()
        DaggerAppComponent.builder().create(this).inject(this)
    }

    override fun activityInjector() = activityInjector

    private fun initializeLogger() {
        Logger.addLogAdapter(object : AndroidLogAdapter() {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return APP_DEBUG
            }
        })
    }
}
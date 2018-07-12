package com.theobencode.victoroben.bottlerocket.di.modules

import android.content.Context
import com.theobencode.victoroben.bottlerocket.MainActivity
import com.theobencode.victoroben.bottlerocket.di.scopes.PerActivity
import dagger.Binds
import dagger.Module


/**
 * Provides dependencies for [MainActivity]
 * */
@Module
abstract class MainActivityModule {

    @Binds
    @PerActivity
    abstract fun activityContext(activity: MainActivity): Context
}
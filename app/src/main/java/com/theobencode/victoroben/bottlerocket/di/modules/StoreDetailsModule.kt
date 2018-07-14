package com.theobencode.victoroben.bottlerocket.di.modules

import android.content.Context
import com.theobencode.victoroben.bottlerocket.di.scopes.PerActivity
import com.theobencode.victoroben.bottlerocket.presentation.storedetails.StoreDetailsActivity
import dagger.Binds
import dagger.Module

/**
 * Provides dependencies for [StoreDetailsActivity]
 * */
@Module
abstract class StoreDetailsModule {

    @Binds
    @PerActivity
    abstract fun activityContext(activity: StoreDetailsActivity): Context
}

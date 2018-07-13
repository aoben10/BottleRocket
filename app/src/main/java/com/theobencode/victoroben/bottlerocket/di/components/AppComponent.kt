package com.theobencode.victoroben.bottlerocket.di.components

import com.theobencode.victoroben.bottlerocket.presentation.BottleRocketApp
import com.theobencode.victoroben.bottlerocket.di.modules.AppModule
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

/**
 * <p>Exposes the dependencies for injection as provided in the modules</p>
 */
@Singleton
@Component(modules = [AppModule::class])
interface AppComponent : AndroidInjector<BottleRocketApp> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<BottleRocketApp>()
}

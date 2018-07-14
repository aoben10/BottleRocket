package com.theobencode.victoroben.bottlerocket.di.modules

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.theobencode.victoroben.bottlerocket.data.repository.StoreRepository
import com.theobencode.victoroben.bottlerocket.data.repository.StoreRepositoryImpl
import com.theobencode.victoroben.bottlerocket.di.ViewModelKey
import com.theobencode.victoroben.bottlerocket.di.scopes.PerActivity
import com.theobencode.victoroben.bottlerocket.presentation.BottleRocketApp
import com.theobencode.victoroben.bottlerocket.presentation.ViewModelFactory
import com.theobencode.victoroben.bottlerocket.presentation.storedetails.StoreDetailsActivity
import com.theobencode.victoroben.bottlerocket.presentation.storeslist.MainActivity
import com.theobencode.victoroben.bottlerocket.presentation.storeslist.StoreViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.multibindings.IntoMap
import javax.inject.Singleton

/**
 * Provides application-wide dependencies.
 */
@Module(includes = [AndroidSupportInjectionModule::class, NetModule::class])
abstract class AppModule {

    /*
    * Singleton annotation isn't necessary since Application instance is unique but is here for
    * convention. In general, providing Activity, Fragment, BroadcastReceiver, etc does not require
    * them to be scoped since they are the components being injected and their instance is unique.
    *
    * However, having a scope annotation makes the module easier to read. We wouldn't have to look
    * at what is being provided in order to understand its scope.
    */
    @Binds
    @Singleton
    abstract fun provideApplication(app: BottleRocketApp): Application

    /**
     * Provides the injector for the [MainActivity], which has access to the dependencies provided
     * by this application instance (singleton scoped objects).
     */
    @PerActivity
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun mainActivityInjector(): MainActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [StoreDetailsModule::class])
    abstract fun storeDetailsInjector(): StoreDetailsActivity

    @Binds
    @IntoMap
    @ViewModelKey(StoreViewModel::class)
    abstract fun provideStoreViewModel(viewModel: StoreViewModel): ViewModel

    @Binds
    abstract fun provideViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    abstract fun bindStoreRepository(storeRepository: StoreRepositoryImpl): StoreRepository

}

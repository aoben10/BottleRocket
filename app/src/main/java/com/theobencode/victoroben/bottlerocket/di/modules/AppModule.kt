package com.theobencode.victoroben.bottlerocket.di.modules

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import com.theobencode.victoroben.bottlerocket.common.ImageLoader
import com.theobencode.victoroben.bottlerocket.common.PicassoImageLoader
import com.theobencode.victoroben.bottlerocket.data.repository.StoreRepository
import com.theobencode.victoroben.bottlerocket.data.repository.StoreRepositoryImpl
import com.theobencode.victoroben.bottlerocket.di.ViewModelKey
import com.theobencode.victoroben.bottlerocket.di.scopes.PerActivity
import com.theobencode.victoroben.bottlerocket.presentation.BottleRocketApp
import com.theobencode.victoroben.bottlerocket.presentation.MainActivity
import com.theobencode.victoroben.bottlerocket.presentation.StoreViewModel
import com.theobencode.victoroben.bottlerocket.presentation.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
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

    @Binds
    @IntoMap
    @ViewModelKey(StoreViewModel::class)
    abstract fun provideStoreViewModel(viewModel: StoreViewModel): ViewModel

    @Binds
    abstract fun provideViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    abstract fun bindStoreRepo(storeRepository: StoreRepositoryImpl): StoreRepository

    @Module
    companion object {

        @Singleton
        @Provides
        fun provideImageLoader(picasso: Picasso): ImageLoader {
            return PicassoImageLoader(picasso)
        }

        @Singleton
        @Provides
        internal fun providePicasso(app: BottleRocketApp): Picasso {
            return Picasso.Builder(app).build()
        }
    }

}

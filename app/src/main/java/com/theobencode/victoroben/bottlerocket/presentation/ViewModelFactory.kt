package com.theobencode.victoroben.bottlerocket.presentation

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

/**
 * A factory used for creating [ViewModel]s
 */
@Suppress("UNCHECKED_CAST")
@Singleton

class ViewModelFactory
@Inject
constructor(private val viewModels: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        var provider: Provider<ViewModel>? = viewModels[modelClass]

        if (provider == null) {
            for ((key, value) in viewModels) {
                if (modelClass.isAssignableFrom(key)) {
                    provider = value
                    break
                }
            }
        }

        if (provider == null) {
            throw IllegalArgumentException("unknown model class $modelClass")
        }

        try {
            return provider.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }
}

package com.theobencode.victoroben.bottlerocket.presentation.storeslist

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.orhanobut.logger.Logger
import com.theobencode.victoroben.bottlerocket.data.repository.Response
import com.theobencode.victoroben.bottlerocket.data.repository.Status
import com.theobencode.victoroben.bottlerocket.data.repository.StoreRepository
import com.theobencode.victoroben.bottlerocket.presentation.model.StoreEntity
import com.theobencode.victoroben.bottlerocket.presentation.model.StoreMapper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class StoreViewModel @Inject constructor(private val storeRepository: StoreRepository,
                                         private val storeMapper: StoreMapper) : ViewModel() {

    var store = MutableLiveData<Response<StoreEntity?>>()
    private val compositeDisposable = CompositeDisposable()

    fun fetchStore() {
        compositeDisposable.add(storeRepository.getStore()
                .doOnSubscribe(::onLoading)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map { storeMapper.mapToPresentation(it) }
                .subscribe(::onSuccess, ::onError))
    }

    private fun onSuccess(storeEntity: StoreEntity?) {
        store.postValue(Response(status = Status.SUCCESS, data = storeEntity, message = null))
    }

    private fun onError(throwable: Throwable) {
        Logger.e("An error occurred while fetching store data", throwable)
        store.postValue(Response(status = Status.ERROR, data = store.value?.data, message = throwable.message))
    }

    private fun onLoading(disposable: Disposable) {
        store.postValue(Response(status = Status.LOADING, data = store.value?.data, message = null))
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

}
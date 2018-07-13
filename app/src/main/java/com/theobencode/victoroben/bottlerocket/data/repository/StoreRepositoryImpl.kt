package com.theobencode.victoroben.bottlerocket.data.repository

import com.theobencode.victoroben.bottlerocket.data.model.StoreData
import com.theobencode.victoroben.bottlerocket.data.remote.RestApiService
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StoreRepositoryImpl @Inject constructor(private val api: RestApiService) : StoreRepository {

    // TODO: Cache data

    override fun getStore(): Single<StoreData> {
        return api.getStore()
    }

}

enum class Status { LOADING, SUCCESS, ERROR }

data class Response<out T> constructor(val status: Status, val data: T? = null, val message: String? = null)
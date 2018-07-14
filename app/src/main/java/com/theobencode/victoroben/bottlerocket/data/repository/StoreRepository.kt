package com.theobencode.victoroben.bottlerocket.data.repository

import com.theobencode.victoroben.bottlerocket.data.model.StoreData
import io.reactivex.Single

interface StoreRepository {
    val key: String

    fun getStore(): Single<StoreData>
}
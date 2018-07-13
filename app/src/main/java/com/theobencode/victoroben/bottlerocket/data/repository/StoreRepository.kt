package com.theobencode.victoroben.bottlerocket.data.repository

import com.theobencode.victoroben.bottlerocket.data.model.StoreData
import io.reactivex.Single

interface StoreRepository {
    fun getStore(): Single<StoreData>
}
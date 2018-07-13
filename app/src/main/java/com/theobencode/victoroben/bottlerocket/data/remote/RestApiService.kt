package com.theobencode.victoroben.bottlerocket.data.remote

import com.theobencode.victoroben.bottlerocket.data.model.StoreData
import io.reactivex.Single
import retrofit2.http.GET

interface RestApiService {

    @GET("/BR_Android_CodingExam_2015_Server/stores.json")
    fun getStore(): Single<StoreData>

}

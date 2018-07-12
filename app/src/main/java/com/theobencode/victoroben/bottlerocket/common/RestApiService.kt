package com.theobencode.victoroben.bottlerocket.common

import com.theobencode.victoroben.bottlerocket.data.net.Store
import io.reactivex.Single
import retrofit2.http.GET

interface RestApiService {

    @GET("/BR_Android_CodingExam_2015_Server/stores.json")
    fun getStore(): Single<Store>

}

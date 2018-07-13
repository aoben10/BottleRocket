package com.theobencode.victoroben.bottlerocket.data.model

import com.google.gson.annotations.SerializedName

data class StoreData(
        @SerializedName("stores") val storeList: List<StoreItemData?>? = null
)
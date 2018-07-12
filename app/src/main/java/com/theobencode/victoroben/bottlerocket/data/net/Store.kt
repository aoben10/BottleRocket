package com.theobencode.victoroben.bottlerocket.data.net

import com.google.gson.annotations.SerializedName

data class Store(
        @SerializedName("stores") val store: List<StoreItem?>? = null
)
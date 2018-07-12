package com.theobencode.victoroben.bottlerocket.data.net

import com.google.gson.annotations.SerializedName

data class StoreItem(

        @SerializedName("zipcode") val zipcode: String? = null,

        @SerializedName("address") val address: String? = null,

        @SerializedName("city") val city: String? = null,

        @SerializedName("phone") val phone: String? = null,

        @SerializedName("latitude") val latitude: String? = null,

        @SerializedName("name") val name: String? = null,

        @SerializedName("storeLogoURL") val storeLogoURL: String? = null,

        @SerializedName("state") val state: String? = null,

        @SerializedName("storeID") val storeID: String? = null,

        @SerializedName("longitude") val longitude: String? = null
)
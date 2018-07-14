package com.theobencode.victoroben.bottlerocket.presentation.model

import com.theobencode.victoroben.bottlerocket.data.model.StoreData
import com.theobencode.victoroben.bottlerocket.data.model.StoreItemData
import javax.inject.Inject

data class StoreEntity(val storeList: List<StoreItemEntity>?)

data class StoreItemEntity(val zipcode: String? = null, val address: String?,
                           val city: String? = null, val phone: String? = null,
                           val latitude: String? = null, val name: String? = null,
                           val storeLogoURL: String? = null, val state: String? = null,
                           val storeID: String? = null, val longitude: String? = null)


class StoreMapper @Inject constructor(private val storeListMapper: StoreListMapper) {
    fun mapToPresentation(storeData: StoreData): StoreEntity = StoreEntity(
            storeListMapper.mapToPresentationList(storeData.storeList))
}

class StoreListMapper @Inject constructor() {
    fun mapToPresentationList(list: List<StoreItemData?>?): List<StoreItemEntity>? = list?.map { mapToPresentation(it) }

    private fun mapToPresentation(storeData: StoreItemData?): StoreItemEntity = StoreItemEntity(
            zipcode = storeData?.zipcode,
            address = storeData?.address,
            city = storeData?.city,
            phone = storeData?.phone,
            latitude = storeData?.latitude,
            name = storeData?.name,
            storeLogoURL = storeData?.storeLogoURL,
            state = storeData?.state,
            storeID = storeData?.storeID,
            longitude = storeData?.longitude
    )
}
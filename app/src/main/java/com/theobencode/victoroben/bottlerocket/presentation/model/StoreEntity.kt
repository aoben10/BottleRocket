package com.theobencode.victoroben.bottlerocket.presentation.model

import com.theobencode.victoroben.bottlerocket.data.model.StoreData
import com.theobencode.victoroben.bottlerocket.data.model.StoreItemData
import javax.inject.Inject

data class StoreEntity(val storeList: List<StoreItemData?>?)

class StoreMapper @Inject constructor() {
    fun mapToPresentation(storeData: StoreData): StoreEntity = StoreEntity(storeData.storeList)
}

package ru.ok.catalog.transport.kmp.models.classification

import kotlinx.serialization.Serializable

@Serializable
data class MpClassificationListFilterDto (
    //id продукта
    val productId: String? = null
)

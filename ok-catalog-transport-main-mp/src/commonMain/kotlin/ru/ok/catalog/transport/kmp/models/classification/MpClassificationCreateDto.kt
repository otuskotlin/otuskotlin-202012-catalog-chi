package ru.ok.catalog.transport.kmp.models.classification

import kotlinx.serialization.Serializable

@Serializable
data class MpClassificationCreateDto (
    override val productId: String? = null,
    override val categoryId: String? = null
) : IMpClassificationCreateDto
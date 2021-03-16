package ru.ok.catalog.transport.kmp.models.classification

import kotlinx.serialization.Serializable

@Serializable
data class MpClassificationDto(
    override val productId: String? = null,
    override val categoryId: String? = null,
    override val id: String? = null
):IMpClassificationDto

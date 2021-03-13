package ru.ok.catalog.transport.kmp.models.classification

import kotlinx.serialization.Serializable

@Serializable
data class MpClassificationUpdateDto(
    override val productId: String?,
    override val categoryId: String?,
    override val id: String?
):IMpClassificationUpdateDto

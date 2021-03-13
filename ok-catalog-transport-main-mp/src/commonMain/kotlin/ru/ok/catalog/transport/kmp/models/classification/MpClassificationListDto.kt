package ru.ok.catalog.transport.kmp.models.classification

import kotlinx.serialization.Serializable
import ru.ok.catalog.transport.kmp.models.category.MpCategoryDto

@Serializable
data class MpClassificationListItemDto(
    override val productId: String? = null,
    override val categoryId: String? = null,
    override val id: String? = null,
    //путь начиная от корня к категории categoryId (включая ее)
    val classificationPath: List<MpCategoryDto>? = null
) : IMpClassificationDto
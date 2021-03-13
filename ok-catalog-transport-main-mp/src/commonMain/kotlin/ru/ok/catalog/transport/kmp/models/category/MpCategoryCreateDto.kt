package ru.ok.catalog.transport.kmp.models.category

import kotlinx.serialization.Serializable

@Serializable
data class MpCategoryCreateDto (
    override val type: String? = null,
    override val code: String? = null,
    override val title: String? = null,
    override val upRefId: String? = null
) : IMpCategoryCreateDto

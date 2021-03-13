package ru.ok.catalog.transport.kmp.models.category

import kotlinx.serialization.Serializable

@Serializable
data class MpCategoryUpdateDto(
    override val type: String? = null,
    override val code: String? = null,
    override val title: String? = null,
    override val upRefId: String? = null,
    override val id: String? = null
) : IMpCategoryUpdateDto

package ru.ok.catalog.transport.kmp.models.category

import kotlinx.serialization.Serializable
import ru.ok.catalog.transport.kmp.models.common.EMpItemPermission

@Serializable
data class MpCategoryDto(
    override val type: String? = null,
    override val code: String? = null,
    override val title: String? = null,
    override val upRefId: String? = null,
    override val isRoot: Boolean? = null,
    override val isLeaf: Boolean? = null,
    override val permissions: Set<EMpItemPermission>? = null,
    override val id: String? = null
) : IMpCategoryDto

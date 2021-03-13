package ru.ok.catalog.transport.kmp.models.category

import kotlinx.serialization.Serializable

@Serializable
data class MpCategoryListFilterDto (
    //тип категории
    val type: String? = null,
    //ссылка на родителя. null для категории верхнего уровня
    val parentId: String? = null
)

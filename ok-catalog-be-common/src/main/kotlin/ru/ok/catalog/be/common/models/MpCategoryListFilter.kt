package ru.ok.catalog.transport.kmp.models.category

import ru.ok.catalog.be.common.models.CategoryType
import ru.ok.catalog.be.common.models.MpCategoryModel


data class MpCategoryListFilter (
    /** тип категории **/
    val type: CategoryType = CategoryType.MARKETPLACE,
    /** ссылка на родителя. null для категории верхнего уровня **/
    val parentId: String = ""
) {
    companion object {
        val NONE = MpCategoryListFilter()
    }
}

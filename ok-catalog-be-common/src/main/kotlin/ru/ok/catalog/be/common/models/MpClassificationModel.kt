package ru.ok.catalog.be.common.models

/**
Классификация.
Реализует отноление N - N между категорией и продуктом.
*/
data class MpClassificationModel(
    /** id классификации */
    override val id: MpClassificationIdModel = MpClassificationIdModel.NONE,

    /** Ссылка на продукт. */
    val productId: MpProductItemIdModel = MpProductItemIdModel.NONE,

    /**
    Ссылка на категорию.
    Ссылаться можно только на последний уровень категорий.
    */
    val categoryId: MpCategoryIdModel = MpCategoryIdModel.NONE,
) : IMpModel {
    companion object {
        val NONE = MpClassificationModel()
    }
}


package ru.ok.catalog.be.common.models

//Классификация
//реализует отноление N - N между категорией и продуктом
data class MpClassificationModel(
    override val id: MpClassificationIdModel = MpClassificationIdModel.NONE,
    //ссылка на продукт
    val productId: MpProductItemIdModel = MpProductItemIdModel.NONE,
    //ссылка на категорию
    //ссылаться можно только на последний уровень категорий
    val categoryId: MpCategoryIdModel = MpCategoryIdModel.NONE,
) : IMpModel {
    companion object {
        val NONE = MpClassificationModel()
    }
}


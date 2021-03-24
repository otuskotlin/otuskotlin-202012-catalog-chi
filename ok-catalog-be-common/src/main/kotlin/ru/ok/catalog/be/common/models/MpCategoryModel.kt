package ru.ok.catalog.be.common.models

/**
Категория - основной элемент системы классификации
*/
data class MpCategoryModel(
    /** id категории **/
    override val id: MpCategoryIdModel = MpCategoryIdModel.NONE,

    /**
    Тип категории.
    Заполняется на всех уровнях.
     */
    val type: CategoryType = CategoryType.MARKETPLACE,
    //    рассмотривал вариант сделать типизацию через upRefId на специальные companion object
    //    отказался из соображений простоты и из-за того, что может потребоваться обработка всех
    //    вариантов, enum дает возможность это контролировать при компиляции

    /** Код категории. Например код ОКВЭД или кода категории бренда. Не обязателен для использования. */
    val code: String = "",

    /** Наименование категории. */
    val title: String = "",

    /** Ссылка на вышестоящий уровень. */
    val upRefId: MpCategoryIdModel = MpCategoryIdModel.NONE,

    //признак верхнего уровня, ведется на уровне BE
    //выглядит ненужным т.к. вычисляется по upRefId = ""
    //val isRoot: Boolean = false,

    /** Признак посреднего уровня. Ведется на уровне BE.*/
    val isLeaf: Boolean? = null,
    //нужен для контроля того, что продукт привязан только к последнему уровню
    //сделан nullable, т.к. он не будет приниматься от FE, и нужно различать эту ситуацию
) : IMpModel {
    companion object {
        val NONE = MpCategoryModel()
    }
}


package ru.ok.catalog.be.common.models

//Категория
//основной элемент системы классификации
data class MpCategoryModel(
    override val id: MpCategoryIdModel = MpCategoryIdModel.NONE,
    //тип категории
    //заполняется на всех уровнях
    //рассмотривал вариант сделать типизацию через upRefId на специальные companion object
    //отказался из соображений простаты и из-за того, что может потребоваться обработка всех
    //вариантов, enum дает возможность это контролировать при компиляции
    val type: EMpCategoryType = EMpCategoryType.MARKETPLACE,
    //код категории, например код ОКВЭД или кода категории бренда
    val code: String = "",
    //наименование
    val title: String = "",
    //ссылка на вышестоящий уровень
    val upRefId: MpCategoryIdModel = MpCategoryIdModel.NONE,

    //признак верхнего уровня, ведется на уровне BE
    //выглядит ненужным т.к. вычисляется по upRefId = ""
    //val isRoot: Boolean = false,
    //признак посреднего уровня, ведется на уровне BE
    //нужен для контроля того, что продукт привязан только к последнему уровню
    //сделан nullable, т.к. он не будет приниматься от FE, и нужно различать эту ситуацию
    val isLeaf: Boolean? = null,
) : IMpModel {
    companion object {
        val NONE = MpCategoryModel()
    }
}


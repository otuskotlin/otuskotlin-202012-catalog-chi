package ru.ok.catalog.be.common.models

//Продукт
//за рамками задания, заведен для моделирования полной функциональности
data class MpProductItemModel(
    override val id: MpProductItemIdModel = MpProductItemIdModel.NONE,
    //наименование
    val title: String = "",
) : IMpModel {
    companion object {
        //понадобится к контексте
        //это синглтон, создается один раз
        //используется, когда нужен пустой объект
        //есть нюанс, поля должны быть иммутабельные - val
        val NONE = MpProductItemModel()
    }
}


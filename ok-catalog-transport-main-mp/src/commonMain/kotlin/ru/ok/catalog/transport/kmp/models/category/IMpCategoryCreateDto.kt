package ru.ok.catalog.transport.kmp.models.category

interface  IMpCategoryCreateDto {
    /** тип категории **/
    val type: String?

    /** код категории, например код ОКВЭД или кода категории бренда **/
    val code: String?

    /** наименование **/
    val title: String?

    /** ссылка на вышестоящий уровень **/
    val upRefId: String?

}
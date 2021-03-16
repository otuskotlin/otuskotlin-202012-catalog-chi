package ru.ok.catalog.transport.kmp.models.classification

interface  IMpClassificationCreateDto {
    //ссылка на продукт
    val productId: String?
    //ссылка на категорию
    val categoryId: String?
}
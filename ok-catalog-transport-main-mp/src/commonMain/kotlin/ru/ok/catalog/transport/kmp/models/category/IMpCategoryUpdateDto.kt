package ru.ok.catalog.transport.kmp.models.category


interface IMpCategoryUpdateDto : IMpCategoryCreateDto {
    //в транспорте не используем inline классы для id
    //здесь нужна простота
    val id: String?
}

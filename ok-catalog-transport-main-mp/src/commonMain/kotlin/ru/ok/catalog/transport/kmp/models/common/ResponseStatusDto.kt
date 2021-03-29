package ru.ok.catalog.transport.kmp.models.common

enum class ResponseStatusDto {
    /**
     * Запрос обработан успешно, статус результата - успех
     **/
    SUCCESS,
    /**
     * Запрос обработан успешно, статус результата - ошибка
     **/
    ERROR,
    /**
     * Запрос не обработан
     **/
    FAIL,
    //BAD_REQUEST,
    //NOT_FOUND,
}

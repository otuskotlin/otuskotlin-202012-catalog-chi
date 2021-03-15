package ru.ok.catalog.transport.kmp.models.category

import ru.ok.catalog.transport.kmp.models.common.EMpItemPermission

/**
 * Интерфейс транспортной модели категории
 */
interface IMpCategoryDto : IMpCategoryUpdateDto {
    /** признак верхнего уровня, ведется на уровне BE **/
    val isRoot: Boolean?
    /** признак посреднего уровня, ведется на уровне BE **/
    val isLeaf: Boolean?

    //TODO: понять, зачем они нужны
    val permissions: Set<EMpItemPermission>?
}
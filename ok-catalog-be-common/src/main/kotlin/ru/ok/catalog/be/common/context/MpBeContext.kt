package ru.ok.catalog.be.common.context

import ru.ok.catalog.be.common.models.MpCategoryIdModel
import ru.ok.catalog.be.common.models.MpCategoryModel
import ru.ok.catalog.be.common.models.MpClassificationIdModel
import ru.ok.catalog.be.common.models.MpClassificationModel

/** Контекст служит контейнером для внутренних моделей
 * и используется в цепочке обработки запроса.

Контекст один на все приложение
экземпляры создаются для каждого запроса
для микросервиса достаточно одного контекста, хотя в общем случае их может быть больше.

 **/
data class MpBeContext(
    var requestCategoryId: MpCategoryIdModel = MpCategoryIdModel.NONE,
    var requestCategory: MpCategoryModel = MpCategoryModel.NONE,
    var requestClassificationId: MpClassificationIdModel = MpClassificationIdModel.NONE,
    var requestClassification: MpClassificationModel = MpClassificationModel.NONE,

    var responseCategory: MpCategoryModel = MpCategoryModel.NONE,
    var responseClassification: MpClassificationModel = MpClassificationModel.NONE,
)

package ru.ok.catalog.be.common.context

import ru.ok.catalog.be.common.models.*
import ru.ok.catalog.transport.kmp.models.category.MpCategoryListFilter
import ru.ok.catalog.transport.kmp.models.classification.MpClassificationListFilter

/** Контекст служит контейнером для внутренних моделей
 * и используется в цепочке обработки запроса.

Контекст один на все приложение
экземпляры создаются для каждого запроса
для микросервиса достаточно одного контекста, хотя в общем случае их может быть больше.

 **/
data class MpBeContext(
    var status: MpBeContextStatus = MpBeContextStatus.NONE,
    var errors: MutableList<IMpError> = mutableListOf(),
    var stubCase: MpStubCase = MpStubCase.NONE,
    var stubParams: Map<String,String> = mapOf(),
    var requestId: String = "",

    var qryCategoryId: MpCategoryIdModel = MpCategoryIdModel.NONE,
    var qryCategory: MpCategoryModel = MpCategoryModel.NONE,
    var qryCategoryFilter: MpCategoryListFilter = MpCategoryListFilter.NONE,

    var qryClassificationId: MpClassificationIdModel = MpClassificationIdModel.NONE,
    var qryClassification: MpClassificationModel = MpClassificationModel.NONE,
    var qryClassificationFilter: MpClassificationListFilter = MpClassificationListFilter.NONE,


    var resCategory: MpCategoryModel = MpCategoryModel.NONE,
    var resCategories: MutableList<MpCategoryModel> = mutableListOf(),
    var resClassification: MpClassificationModel = MpClassificationModel.NONE,
    var resClassifications: MutableList<MpClassificationModel> = mutableListOf(),
)

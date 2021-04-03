package ru.ok.catalog.be.mappers

import ru.ok.catalog.be.common.context.IMpError
import ru.ok.catalog.be.common.context.MpBeContext
import ru.ok.catalog.be.common.context.MpBeContextStatus
import ru.ok.catalog.be.common.models.*
import ru.ok.catalog.transport.kmp.models.category.*
import ru.ok.catalog.transport.kmp.models.common.ResponseStatusDto
import ru.ok.catalog.transport.kmp.models.common.StubCase

//TODO реализовать или удалить
//fun MpFrontContext.responded(data: MpResponseCategoryList) = apply {
//    demands = data.demands?.map { it.toInternal() }?.toMutableList() ?: mutableListOf()
//}
//
//fun MpFrontContext.responded(data: MpResponseCategoryCreate) = apply {
//    demand = data.demand?.toInternal() ?: CategoryModel.NONE
//}
//
//fun MpFrontContext.responded(data: MpResponseCategoryRead) = apply {
//    demand = data.demand?.toInternal() ?: CategoryModel.NONE
//}
//
//fun MpFrontContext.responded(data: MpResponseCategoryUpdate) = apply {
//    demand = data.demand?.toInternal() ?: CategoryModel.NONE
//}
//
//fun MpFrontContext.responded(data: MpResponseCategoryDelete) = apply {
//    if (data.deleted == true) {
//        demand = CategoryModel.NONE
//    }
//}

//fun MpBeContext.init(request: IMpRequest) =
//    when(request){
//        is MpRequestCategoryCreate -> this.init(request)
//        is MpRequestCategoryRead -> this.init(request)
//        is MpRequestCategoryUpdate -> this.init(request)
//        is MpRequestCategoryDelete -> this.init(request)
//        else -> null
//    }

fun MpBeContext.init(request: MpRequestCategoryCreate): MpBeContext {
    requestId = request.requestId.toString()
    stubCase = when(request.debug?.stubCase) {
        "SUCCESS" -> MpStubCase.CATEGORY_CREATE_SUCCESS
        else -> MpStubCase.INVALID
    }
    request.createData?.let {
        qryCategory = it.toInternal()
    }
    return this
}

fun MpBeContext.init(request: MpRequestCategoryRead): MpBeContext {
    requestId = request.requestId.toString()
    stubCase = when(request.debug?.stubCase) {
        "SUCCESS" -> MpStubCase.CATEGORY_READ_SUCCESS
        "ERROR" -> MpStubCase.CATEGORY_READ_ERROR
        "EXCEPTION" -> MpStubCase.CATEGORY_READ_EXCEPTION
        else -> MpStubCase.INVALID
    }
    //this.requestCategoryId = request.categoryId?.let { MpCategoryIdModel(it) }?: MpCategoryIdModel.NONE
    this.qryCategoryId = if ( request.categoryId == null ) {
        MpCategoryIdModel.NONE
    } else {
        MpCategoryIdModel(request.categoryId!!)
    }
    return this
}

fun MpBeContext.init(request: MpRequestCategoryUpdate): MpBeContext {
    request.updateData?.let { data ->
        this.qryCategory = MpCategoryModel(
            title = data.title ?: "",
            code = data.code ?: "",
            //TODO: где делать валидацию type и возвращать ошибку?
            type = CategoryType.valueOf(data.type ?: "NONE"),
            upRefId = MpCategoryIdModel(data.upRefId ?: ""),
            //TODO: валидация на непустое значение
            id = MpCategoryIdModel(data.id ?: ""),
        )
    }
    return this
}

fun MpBeContext.init(request: MpRequestCategoryDelete): MpBeContext {
    this.qryCategoryId = if ( request.categoryId == null ) {
        MpCategoryIdModel.NONE
    } else {
        MpCategoryIdModel(request.categoryId!!)
    }
    return this
}

fun MpBeContext.init(request: MpRequestCategoryList): MpBeContext {
    requestId = request.requestId ?: ""
    if ( requestId == "") {
        errors.add( MpError( code = "MP-E-", message = "Не задан requestId", field = "requestId" ))
    }

    if ( request.filterData == null ) {
        errors.add( MpError( code = "MP-E-", message = "Не задан фильтр", field = "filterData" ))
    } else {
        qryCategoryFilter = request.filterData!!.toInternal(this)
    }

    stubCase = when(request.debug?.stubCase) {
        "SUCCESS" -> MpStubCase.CATEGORY_LIST_SUCCESS
        else -> MpStubCase.INVALID
    }

    if ( stubCase == MpStubCase.INVALID) {
        errors.add( MpError( code = "MP-E-", message = "Недопустимое значение <${request.debug?.stubCase.toString()}>", field = "stubCase" ))
    }
    return this
}

inline fun <reified T : Enum<T>> String?.asEnumOrDefault(defaultValue: T): T =
    enumValues<T>().firstOrNull { it.name.equals(this, ignoreCase = true) } ?: defaultValue

fun MpCategoryDto.toInternal() = MpCategoryModel(
    id = id?.let { MpCategoryIdModel(it) } ?: MpCategoryIdModel.NONE,
    title = title ?: "",
    code = code ?: "",
    //TODO
    //type = type.asEnumOrDefault()
)

fun MpCategoryCreateDto.toInternal() = MpCategoryModel(
    title = title ?: "",
    code = code ?: "",
    //TODO
    //type =
)

fun MpCategoryListFilterDto.toInternal(ctx: MpBeContext): MpCategoryListFilter {
    val intType = type.asEnumOrDefault(CategoryType.INVALID)
    if ( intType == CategoryType.INVALID) {
        ctx.errors.add( MpError( code = "MP-E-", message = "Недопустимое значение <$type>", field = "type" ))
    }
    return MpCategoryListFilter(
        type = intType,
        parentId = parentId ?: "",
    )
}


fun MpCategoryModel.toDto() =
    if ( this == MpCategoryModel.NONE ) {
        null
    } else {
        MpCategoryDto(
            //someObject?.takeIf{ status }?.apply{ doThis() }
            //someObject?.takeIf{ status }?.doThis()
            //id = this.id.id.takeIf{it.isNotBlank()},    //для преобразования пустых строк в null
            id = this.id.id.bnl(),
            title = this.title,
            code = this.code,
            type = this.type.name,
            upRefId = this.upRefId.id.bnl(),
            isLeaf = this.isLeaf,
            isRoot = this.upRefId.id == ""
        )
    }

fun MpBeContextStatus.toDto() =
    when(this) {
        MpBeContextStatus.SUCCESS -> ResponseStatusDto.SUCCESS
        MpBeContextStatus.ERROR -> ResponseStatusDto.ERROR
        else -> ResponseStatusDto.FAIL
    }

fun  String.bnl(): String? {
    return if ( this == "") { null } else { this }
}


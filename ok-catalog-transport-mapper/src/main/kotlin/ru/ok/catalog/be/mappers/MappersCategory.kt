package ru.ok.catalog.be.mappers

import ru.ok.catalog.be.common.context.IMpError
import ru.ok.catalog.be.common.context.MpBeContext
import ru.ok.catalog.be.common.context.MpBeContextStatus
import ru.ok.catalog.be.common.models.*
import ru.ok.catalog.transport.kmp.models.category.*
import ru.ok.catalog.transport.kmp.models.common.ResponseStatusDto


// В добротно сформированном сообщении об ошибке указываются
// данные, которые привели к ощибке. Первичная валидация
// делается здесь т.к. здесь эти данные есть и они "в руках".

/***********************************************************
 *  Create - DONE
 ***********************************************************/
fun MpBeContext.init(request: MpRequestCategoryCreate): MpBeContext {
    requestId = request.requestId.toString()

    stubCase = when(request.debug?.stubCase) {
        "SUCCESS" -> MpStubCase.CATEGORY_CREATE_SUCCESS
        "EXCEPTION" -> MpStubCase.CATEGORY_CREATE_EXCEPTION
        "ERROR" -> MpStubCase.CATEGORY_CREATE_ERROR
        null -> MpStubCase.NONE
        else -> MpStubCase.INVALID
    }
    if ( stubCase == MpStubCase.INVALID) {
        errors.add( MpError( code = "MP-E-0019", message = "Недопустимое значение <${request.debug?.stubCase}>", field = "stubCase", group = IMpError.Group.VALIDATION ))
    }

    request.createData?.let {
        qryCategory = it.toInternal(this)
    }
    return this
}

fun MpCategoryCreateDto.toInternal(ctx: MpBeContext): MpCategoryModel {
    val intType = type.asEnumOrDefault(CategoryType.INVALID)
    if ( intType == CategoryType.INVALID) {
        ctx.errors.add( MpError( code = "MP-E-0010", message = "Недопустимое значение <$type>", field = "type" ))
    }

    return MpCategoryModel(
        title = title ?: "",
        code = code ?: "",
        type = intType,
        upRefId = upRefId?.let { MpCategoryIdModel(it) } ?: MpCategoryIdModel.NONE
    )
}


/***********************************************************
 *  Read - DONE
 ***********************************************************/
fun MpBeContext.init(request: MpRequestCategoryRead): MpBeContext {
    requestId = request.requestId ?: ""
    if ( requestId == "") {
        errors.add( MpError( code = "MP-E-0001", message = "Не задан requestId", field = "requestId" ))
    }

    stubCase = when(request.debug?.stubCase) {
        "SUCCESS" -> MpStubCase.CATEGORY_READ_SUCCESS
        "ERROR" -> MpStubCase.CATEGORY_READ_ERROR
        "EXCEPTION" -> MpStubCase.CATEGORY_READ_EXCEPTION
        null -> MpStubCase.NONE
        else -> MpStubCase.INVALID
    }

    if ( stubCase == MpStubCase.INVALID) {
        errors.add( MpError( code = "MP-E-0002", message = "Недопустимое значение <${request.debug?.stubCase}>", field = "stubCase", group = IMpError.Group.VALIDATION ))
    }

    //поля нижет валидируются в транспонтно-независимой библиотеке
    this.qryCategoryId = if ( request.categoryId == null ) {
        MpCategoryIdModel.NONE
    } else {
        MpCategoryIdModel(request.categoryId!!)
    }
    return this
}

/***********************************************************
 *  Update - DONE
 ***********************************************************/
fun MpBeContext.init(request: MpRequestCategoryUpdate): MpBeContext {
    requestId = request.requestId ?: ""
    if ( requestId == "") {
        errors.add( MpError( code = "MP-E-0025", message = "Не задан requestId", field = "requestId" ))
    }

    stubCase = when(request.debug?.stubCase) {
        "SUCCESS" -> MpStubCase.CATEGORY_UPDATE_SUCCESS
        "ERROR" -> MpStubCase.CATEGORY_UPDATE_ERROR
        "EXCEPTION" -> MpStubCase.CATEGORY_UPDATE_EXCEPTION
        null -> MpStubCase.NONE
        else -> MpStubCase.INVALID
    }

    request.updateData?.let {
        qryCategory = it.toInternal(this)
    }

    return this
}

fun MpCategoryUpdateDto.toInternal(ctx: MpBeContext): MpCategoryModel {
    val intType = type.asEnumOrDefault(CategoryType.INVALID)
    if ( intType == CategoryType.INVALID) {
        ctx.errors.add( MpError( code = "MP-E-0026", message = "Недопустимое значение <$type>", field = "type" ))
    }

    return MpCategoryModel(
        id = id?.let { MpCategoryIdModel(it) } ?: MpCategoryIdModel.NONE,
        title = title ?: "",
        code = code ?: "",
        type = intType,
        upRefId = upRefId?.let { MpCategoryIdModel(it) } ?: MpCategoryIdModel.NONE
    )
}

/***********************************************************
 *  Delete - DONE
 ***********************************************************/
fun MpBeContext.init(request: MpRequestCategoryDelete): MpBeContext {
    requestId = request.requestId ?: ""
    if ( requestId == "") {
        errors.add( MpError( code = "MP-E-0033", message = "Не задан requestId", field = "requestId" ))
    }

    stubCase = when(request.debug?.stubCase) {
        "SUCCESS" -> MpStubCase.CATEGORY_DELETE_SUCCESS
        "ERROR" -> MpStubCase.CATEGORY_DELETE_ERROR
        "EXCEPTION" -> MpStubCase.CATEGORY_DELETE_EXCEPTION
        null -> MpStubCase.NONE
        else -> MpStubCase.INVALID
    }
    if ( stubCase == MpStubCase.INVALID) {
        errors.add( MpError( code = "MP-E-0027", message = "Недопустимое значение <${request.debug?.stubCase}>", field = "stubCase" ))
    }

    //поля нижет валидируются в транспонтно-независимой библиотеке
    this.qryCategoryId  = request.categoryId?.let { MpCategoryIdModel(it) } ?: MpCategoryIdModel.NONE
    return this
}

/***********************************************************
 *  List - DONE
 ***********************************************************/
fun MpBeContext.init(request: MpRequestCategoryList): MpBeContext {
    requestId = request.requestId ?: ""
    if ( requestId == "") {
        errors.add( MpError( code = "MP-E-0003", message = "Не задан requestId", field = "requestId" ))
    }

    if ( request.filterData == null ) {
        errors.add( MpError( code = "MP-E-0004", message = "Не задан фильтр", field = "filterData" ))
    } else {
        qryCategoryFilter = request.filterData!!.toInternal(this)
    }

    stubCase = when(request.debug?.stubCase) {
        "SUCCESS" -> MpStubCase.CATEGORY_LIST_SUCCESS
        null -> MpStubCase.NONE
        else -> MpStubCase.INVALID
    }
    if ( stubCase == MpStubCase.INVALID) {
        errors.add( MpError( code = "MP-E-0005", message = "Недопустимое значение <${request.debug?.stubCase}>", field = "stubCase" ))
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

fun MpCategoryListFilterDto.toInternal(ctx: MpBeContext): MpCategoryListFilter {
    val intType = type.asEnumOrDefault(CategoryType.INVALID)
    if ( intType == CategoryType.INVALID) {
        ctx.errors.add( MpError( code = "MP-E-0006", message = "Недопустимое значение <$type>", field = "type" ))
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


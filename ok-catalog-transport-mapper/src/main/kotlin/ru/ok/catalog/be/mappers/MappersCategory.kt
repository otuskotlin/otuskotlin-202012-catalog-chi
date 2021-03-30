package ru.ok.catalog.be.mappers

import ru.ok.catalog.be.common.context.MpBeContext
import ru.ok.catalog.be.common.models.CategoryType
import ru.ok.catalog.be.common.models.MpCategoryIdModel
import ru.ok.catalog.be.common.models.MpCategoryModel
import ru.ok.catalog.be.common.models.MpStubCase
import ru.ok.catalog.transport.kmp.models.category.*
import ru.ok.catalog.transport.kmp.models.common.IMpRequest
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

private fun MpBeContext.init(request: IMpRequest) =
    when(request){
        is MpRequestCategoryCreate -> this.init(request)
        is MpRequestCategoryRead -> this.init(request)
        is MpRequestCategoryUpdate -> this.init(request)
        is MpRequestCategoryDelete -> this.init(request)
        else -> null
    }

fun MpBeContext.init(request: MpRequestCategoryCreate){
    requestId = request.requestId.toString()
    stubCase = when(request.debug?.stubCase) {
        StubCase.SUCCESS -> MpStubCase.CATEGORY_CREATE_SUCCESS
        null -> MpStubCase.NONE
    }
    request.createData?.let {
        requestCategory = it.toInternal()
    }
}

fun MpBeContext.init(request: MpRequestCategoryRead) {
    requestId = request.requestId.toString()
    stubCase = when(request.debug?.stubCase) {
        StubCase.SUCCESS -> MpStubCase.CATEGORY_READ_SUCCESS
        null -> MpStubCase.NONE
    }
    //this.requestCategoryId = request.categoryId?.let { MpCategoryIdModel(it) }?: MpCategoryIdModel.NONE
    this.requestCategoryId = if ( request.categoryId == null ) {
        MpCategoryIdModel.NONE
    } else {
        MpCategoryIdModel(request.categoryId!!)
    }
}

private fun MpBeContext.init(request: MpRequestCategoryUpdate) {
    request.updateData?.let { data ->
        this.requestCategory = MpCategoryModel(
            title = data.title ?: "",
            code = data.code ?: "",
            //TODO: где делать валидацию type и возвращать ошибку?
            type = CategoryType.valueOf(data.type ?: "MARKETPLACE"),
            upRefId = MpCategoryIdModel(data.upRefId ?: ""),
            //TODO: валидация на непустое значение
            id = MpCategoryIdModel(data.id ?: ""),
        )
    }
}

private fun MpBeContext.init(request: MpRequestCategoryDelete) {
    this.requestCategoryId = if ( request.categoryId == null ) {
        MpCategoryIdModel.NONE
    } else {
        MpCategoryIdModel(request.categoryId!!)
    }
}


fun MpCategoryDto.toInternal() = MpCategoryModel(
    id = id?.let { MpCategoryIdModel(it) } ?: MpCategoryIdModel.NONE,
    title = title ?: "",
    code = code ?: "",
    //TODO
    //type =
)

fun MpCategoryCreateDto.toInternal() = MpCategoryModel(
    title = title ?: "",
    code = code ?: "",
    //TODO
    //type =
)
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

fun  String.bnl(): String? {
    return if ( this == "") { null } else { this }
}

package ru.ok.catalog.transport.kmp.models.category

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.ok.catalog.transport.kmp.models.common.*

@Serializable
@SerialName("MpRequestCategoryList")
data class MpRequestCategoryList(
    override val requestId: String? = null,
    override val onResponseId: String? = null,
    override val timeStart: String? = null,
    override val debug: MpRequestCategoryList.Debug? = null,
    /** ниже специфика запроса **/
    val filterData: MpCategoryListFilterDto? = null
) : IMpRequest, MpMessage() {
    @Serializable
    data class Debug(
        override val mode: MpWorkModeDto?,
        override val stubCase: String?,
        override val stubParams: Map<String, String>? = null
    ) : IMpDebug
}


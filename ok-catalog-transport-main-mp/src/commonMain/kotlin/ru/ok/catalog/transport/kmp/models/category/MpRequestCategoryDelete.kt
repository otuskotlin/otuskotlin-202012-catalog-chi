package ru.ok.catalog.transport.kmp.models.category

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.ok.catalog.transport.kmp.models.common.*

@Serializable
@SerialName("MpRequestCategoryDelete")
data class MpRequestCategoryDelete(
    override val requestId: String? = null,
    override val onResponseId: String? = null,
    override val timeStart: String? = null,
    override val debug: Debug? = null,
    /** ниже специфика запроса **/
    val categoryId: String?  = null,
    val deleteSubCategory: Boolean = false
) : IMpRequest, MpMessage() {

    @Serializable
    data class Debug(
        override val mode: MpWorkModeDto?,
        override val stubCase: String?
    ) : IMpDebug
}


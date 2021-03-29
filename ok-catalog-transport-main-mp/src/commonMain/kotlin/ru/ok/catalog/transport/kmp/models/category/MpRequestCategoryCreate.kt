package ru.ok.catalog.transport.kmp.models.category

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.ok.catalog.transport.kmp.models.common.*

@Serializable
@SerialName("MpRequestCategoryCreate")
data class MpRequestCategoryCreate(
    override val requestId: String? = null,
    override val onResponseId: String? = null,
    override val timeStart: String? = null,
    override val debug: Debug? = null,
    /** ниже специфика запроса **/
    val createData: MpCategoryCreateDto? = null
) : IMpRequest, MpMessage() {
    @Serializable
    data class Debug(
        override val mode: MpWorkModeDto?,
        override val stubCase: StubCase?
    ) : IMpDebug
}


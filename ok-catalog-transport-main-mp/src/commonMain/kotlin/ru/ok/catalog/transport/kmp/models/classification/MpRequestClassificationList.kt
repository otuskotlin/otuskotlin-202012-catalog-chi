package ru.ok.catalog.transport.kmp.models.classification

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.ok.catalog.transport.kmp.models.common.*

@Serializable
@SerialName("MpRequestClassificationList")
data class MpRequestClassificationList (
    override val requestId: String? = null,
    override val onResponseId: String? = null,
    override val timeStart: String? = null,
    override val debug: Debug? = null,
    //ниже специфика запроса
    val filterData: MpClassificationListFilterDto? = null,
):    IMpRequest, MpMessage() {
    @Serializable
    data class Debug(
        override val mode: MpWorkModeDto?,
        override val stubCase: StubCase?
    ) : IMpDebug
}

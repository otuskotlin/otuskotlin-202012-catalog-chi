package ru.ok.catalog.transport.kmp.models.classification

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.ok.catalog.transport.kmp.models.common.*

@Serializable
@SerialName("MpResponseClassificationList")
data class MpResponseClassificationList(
    override val responseId: String? = null,
    override val onRequestId: String? = null,
    override val endTime: String? = null,
    override val errors: List<ErrorDto>? = null,
    override val status: ResponseStatusDto? = null,
    override val debug: Debug? = null,
    //ниже специфика запроса
    val classifications: List<MpClassificationListItemDto>? = null
) : IMpResponse, MpMessage() {
    @Serializable
    data class Debug(
        override val mode: MpWorkModeDto?,
        override val stubCase: String?,
        override val stubParams: Map<String, String>?,
    ) : IMpDebug
}

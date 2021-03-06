package ru.ok.catalog.transport.kmp.models.classification

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.ok.catalog.transport.kmp.models.common.*

@Serializable
@SerialName("MpResponseClassificationCreate")
data class MpResponseClassificationCreate(
    override val responseId: String? = null,
    override val onRequestId: String? = null,
    override val endTime: String? = null,
    override val errors: List<ErrorDto>? = null,
    override val status: EResponseStatusDto? = null,
    override val debug: Debug? = null,
    //ниже специфика запроса
    val classification: MpClassificationDto
) : IMpResponse, MpMessage() {
    @Serializable
    data class Debug(
        override val mode: EMpWorkModeDto?
    ) : IMpDebug
}

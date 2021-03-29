package ru.ok.catalog.transport.kmp.models.category

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.ok.catalog.transport.kmp.models.common.*

@Serializable
@SerialName("MpResponseCategoryDelete")
data class MpResponseCategoryDelete(
    override val responseId: String? = null,
    override val onRequestId: String? = null,
    override val endTime: String? = null,
    override val errors: List<ErrorDto>? = null,
    override val status: ResponseStatusDto? = null,
    override val debug: Debug? = null,
    /** ниже специфика запроса **/
    val category: MpCategoryDto? = null,
) : IMpResponse, MpMessage() {

    @Serializable
    data class Debug(
        override val mode: MpWorkModeDto?,
        override val stubCase: StubCase?
    ) : IMpDebug
}

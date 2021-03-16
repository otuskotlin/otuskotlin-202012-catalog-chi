package ru.ok.catalog.transport.kmp.models.classification

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.ok.catalog.transport.kmp.models.common.EMpWorkModeDto
import ru.ok.catalog.transport.kmp.models.common.IMpDebug
import ru.ok.catalog.transport.kmp.models.common.IMpRequest
import ru.ok.catalog.transport.kmp.models.common.MpMessage

@Serializable
@SerialName("MpRequestClassificationDelete")
data class MpRequestClassificationDelete(
    override val requestId: String? = null,
    override val onResponseId: String? = null,
    override val timeStart: String? = null,
    override val debug: Debug? = null,
    //ниже специфика запроса
    val classificationId: String?  = null
) : IMpRequest, MpMessage() {

    @Serializable
    data class Debug(
        override val mode: EMpWorkModeDto?
    ) : IMpDebug
}

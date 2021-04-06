package ru.ok.catalog.transport.kmp.models.classification

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.ok.catalog.transport.kmp.models.common.*

@Serializable
@SerialName("MpRequestClassificationCreate")
data class MpRequestClassificationCreate(
    override val requestId: String? = null,
    override val onResponseId: String? = null,
    override val timeStart: String? = null,
    override val debug: Debug? = null,
    //ниже специфика запроса
    val createData: MpClassificationCreateDto? = null
) : IMpRequest, MpMessage() {
    @Serializable
    data class Debug(
        override val mode: MpWorkModeDto?,
        override val stubCase: String?,
        override val stubParams: Map<String, String>?,
    ) : IMpDebug

//    override fun toString(): String {
//        return "xxx"
//    }
}

package ru.ok.catalog.transport.kmp.models.category

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.ok.catalog.transport.kmp.models.common.*

@Serializable
@SerialName("MpResponseCategoryRead")
//эта аннотация приводит к тому, что при серилизации в JSON будет
//добавлятся поле type в котором будет значение, указанное в аннотации
//поле type будет использоваться при десериализации
data class MpResponseCategoryRead(
    override val responseId: String? = null,
    override val onRequestId: String? = null,
    override val endTime: String? = null,
    override val errors: List<ErrorDto>? = null,
    override val status: ResponseStatusDto? = null,
    override val debug: Debug? = null,
    /** ниже специфика запроса **/
    val category: MpCategoryDto?  = null,
) : IMpResponse, MpMessage() {

    @Serializable
    data class Debug(
        override val mode: MpWorkModeDto?,
        override val stubCase: String?,
        override val stubParams: Map<String, String>? = null
    ) : IMpDebug
}
//MpMessage - workaround для обхода неких проблем с тем, что JS
//не дружит с полиморфизмом интерфейсов
//MpMessage должен использоваться в каждом классе запроса и ответа

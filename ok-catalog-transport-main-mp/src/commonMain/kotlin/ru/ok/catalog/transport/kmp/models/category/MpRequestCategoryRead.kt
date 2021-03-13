package ru.ok.catalog.transport.kmp.models.category

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.ok.catalog.transport.kmp.models.common.IMpDebug
import ru.ok.catalog.transport.kmp.models.common.IMpRequest
import ru.ok.catalog.transport.kmp.models.common.MpMessage
import ru.ok.catalog.transport.kmp.models.common.EMpWorkModeDto

@Serializable
@SerialName("MpRequestCategoryRead")
//эта аннотация приводит к тому, что при серилизации в JSON бдет
//добавлятся поле type в котором будет значение, указанное в аннотации
//поле type будет использоваться при десериализации
data class MpRequestCategoryRead(
    override val requestId: String? = null,
    override val onResponseId: String? = null,
    override val timeStart: String? = null,
    override val debug: Debug? = null,
    //ниже специфика запроса
    val categoryId: String?  = null
) : IMpRequest, MpMessage() {

    @Serializable
    data class Debug(
        override val mode: EMpWorkModeDto?
    ) : IMpDebug
}
//MpMessage - workaround для обхода неких проблем с тем, что JS
//не дружит с полиморфизмом интерфейсов
//MpMessage должен использоваться в каждом классе запроса и ответа

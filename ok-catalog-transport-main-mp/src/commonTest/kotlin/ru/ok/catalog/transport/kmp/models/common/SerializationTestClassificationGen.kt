package ru.ok.catalog.transport.kmp.models.common

import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import ru.ok.catalog.transport.kmp.models.classification.*

internal class SerializationTestClassificationGen {
    @Test
    fun requestSerialTestClassification() {
        val json = Json{
            prettyPrint = true
            //используем полиморфизм, указыаем базовый класс
            serializersModule = SerializersModule {
                polymorphic(MpMessage::class) {
                    //TODO: не забываем добавлять сюда классы
                    subclass(MpRequestClassificationCreate::class)
                    subclass(MpRequestClassificationDelete::class)
                    subclass(MpRequestClassificationList::class)
                    subclass(MpResponseClassificationCreate::class)
                    subclass(MpResponseClassificationDelete::class)
                    subclass(MpResponseClassificationList::class)
                }
            }
            //указыаем поле по которому будет дескриминатор
            classDiscriminator = "type"
        }

        //
        // проверка Create Classification
        // generated 12.03.2021 14:28:44
        //
        val reqCreate = MpRequestClassificationCreate(
            createData = MpClassificationCreateDto(
                productId = "Test for Create request",
            )
        )
        val reqCreateStr = json.encodeToString(MpMessage.serializer(), reqCreate)
        assertTrue{ reqCreateStr.contains("Test for Create request")}
        val dtoReqCreate = json.decodeFromString(MpMessage.serializer(), reqCreateStr)
        assertEquals(reqCreate, (dtoReqCreate as? MpRequestClassificationCreate))

        val resCreate = MpResponseClassificationCreate(
            classification = MpClassificationDto(
                id = "Test for Create response",
            )
        )
        val resCreateStr = json.encodeToString(MpMessage.serializer(), resCreate)
        assertTrue{ resCreateStr.contains("Test for Create response")}
        val dtoResCreate = json.decodeFromString(MpMessage.serializer(), resCreateStr)
        assertEquals(resCreate, (dtoResCreate as? MpResponseClassificationCreate))
        //
        // проверка Delete Classification
        // generated 12.03.2021 14:28:44
        //
        val reqDelete = MpRequestClassificationDelete(
            classificationId = "Test for Delete request",
        )
        val reqDeleteStr = json.encodeToString(MpMessage.serializer(), reqDelete)
        assertTrue{ reqDeleteStr.contains("Test for Delete request")}
        val dtoReqDelete = json.decodeFromString(MpMessage.serializer(), reqDeleteStr)
        assertEquals(reqDelete, (dtoReqDelete as? MpRequestClassificationDelete))

        val resDelete = MpResponseClassificationDelete(
            classification = MpClassificationDto(
                id = "Test for Delete response",
            )
        )
        val resDeleteStr = json.encodeToString(MpMessage.serializer(), resDelete)
        assertTrue{ resDeleteStr.contains("Test for Delete response")}
        val dtoResDelete = json.decodeFromString(MpMessage.serializer(), resDeleteStr)
        assertEquals(resDelete, (dtoResDelete as? MpResponseClassificationDelete))
        //
        // проверка List Classification
        // generated 12.03.2021 14:28:44
        //
        val reqList = MpRequestClassificationList(
            filterData = MpClassificationListFilterDto(
                productId = "Test for List request"
            )
        )
        val reqListStr = json.encodeToString(MpMessage.serializer(), reqList)
        assertTrue{ reqListStr.contains("Test for List request")}
        val dtoReqList = json.decodeFromString(MpMessage.serializer(), reqListStr)
        assertEquals(reqList, (dtoReqList as? MpRequestClassificationList))

        val resList = MpResponseClassificationList(
            classifications = listOf(MpClassificationListItemDto(
                id = "Test for List response",
            ))
        )
        val resListStr = json.encodeToString(MpMessage.serializer(), resList)
        assertTrue{ resListStr.contains("Test for List response")}
        val dtoResList = json.decodeFromString(MpMessage.serializer(), resListStr)
        assertEquals(resList, (dtoResList as? MpResponseClassificationList))

    }
}
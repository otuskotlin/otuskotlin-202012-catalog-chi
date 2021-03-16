package ru.ok.catalog.transport.kmp.models.common

import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import ru.ok.catalog.transport.kmp.models.category.*
import ru.ok.catalog.transport.kmp.models.classification.MpClassificationCreateDto
import ru.ok.catalog.transport.kmp.models.classification.MpRequestClassificationCreate
import ru.ok.catalog.transport.kmp.models.classification.MpRequestClassificationDelete
import ru.ok.catalog.transport.kmp.models.classification.MpRequestClassificationList

internal class SerializationTestCategoryGen {
    //
    // проверка Create Category
    // generated 16.03.2021 11:37:37
    //

    @Test
    fun test_s12n_Category_Create() {
        val json = prepareJson()

        val reqCreate = MpRequestCategoryCreate(
            createData = MpCategoryCreateDto(
                title = "Test for Create request",
            )
        )
        val reqCreateStr = json.encodeToString(MpMessage.serializer(), reqCreate)
        assertTrue{ reqCreateStr.contains("Test for Create request")}
        val dtoReqCreate = json.decodeFromString(MpMessage.serializer(), reqCreateStr)
        assertEquals(reqCreate, (dtoReqCreate as? MpRequestCategoryCreate))

        val resCreate = MpResponseCategoryCreate(
            category = MpCategoryDto(
                id = "Test for Create response",
            )
        )
        val resCreateStr = json.encodeToString(MpMessage.serializer(), resCreate)
        assertTrue{ resCreateStr.contains("Test for Create response")}
        val dtoResCreate = json.decodeFromString(MpMessage.serializer(), resCreateStr)
        assertEquals(resCreate, (dtoResCreate as? MpResponseCategoryCreate))
    }
    //
    // проверка Read Category
    // generated 16.03.2021 11:37:37
    //

    @Test
    fun test_s12n_Category_Read() {
        val json = prepareJson()

        val reqRead = MpRequestCategoryRead(
            categoryId = "Test for Read request",
        )
        val reqReadStr = json.encodeToString(MpMessage.serializer(), reqRead)
        assertTrue{ reqReadStr.contains("Test for Read request")}
        val dtoReqRead = json.decodeFromString(MpMessage.serializer(), reqReadStr)
        assertEquals(reqRead, (dtoReqRead as? MpRequestCategoryRead))

        val resRead = MpResponseCategoryRead(
            category = MpCategoryDto(
                id = "Test for Read response",
            )
        )
        val resReadStr = json.encodeToString(MpMessage.serializer(), resRead)
        assertTrue{ resReadStr.contains("Test for Read response")}
        val dtoResRead = json.decodeFromString(MpMessage.serializer(), resReadStr)
        assertEquals(resRead, (dtoResRead as? MpResponseCategoryRead))
    }
    //
    // проверка Update Category
    // generated 16.03.2021 11:37:37
    //

    @Test
    fun test_s12n_Category_Update() {
        val json = prepareJson()

        val reqUpdate = MpRequestCategoryUpdate(
            updateData = MpCategoryUpdateDto(
                title = "Test for Update request",
            )
        )
        val reqUpdateStr = json.encodeToString(MpMessage.serializer(), reqUpdate)
        assertTrue{ reqUpdateStr.contains("Test for Update request")}
        val dtoReqUpdate = json.decodeFromString(MpMessage.serializer(), reqUpdateStr)
        assertEquals(reqUpdate, (dtoReqUpdate as? MpRequestCategoryUpdate))

        val resUpdate = MpResponseCategoryUpdate(
            category = MpCategoryDto(
                id = "Test for Update response",
            )
        )
        val resUpdateStr = json.encodeToString(MpMessage.serializer(), resUpdate)
        assertTrue{ resUpdateStr.contains("Test for Update response")}
        val dtoResUpdate = json.decodeFromString(MpMessage.serializer(), resUpdateStr)
        assertEquals(resUpdate, (dtoResUpdate as? MpResponseCategoryUpdate))
    }
    //
    // проверка Delete Category
    // generated 16.03.2021 11:37:37
    //

    @Test
    fun test_s12n_Category_Delete() {
        val json = prepareJson()

        val reqDelete = MpRequestCategoryDelete(
            categoryId = "Test for Delete request",
        )
        val reqDeleteStr = json.encodeToString(MpMessage.serializer(), reqDelete)
        assertTrue{ reqDeleteStr.contains("Test for Delete request")}
        val dtoReqDelete = json.decodeFromString(MpMessage.serializer(), reqDeleteStr)
        assertEquals(reqDelete, (dtoReqDelete as? MpRequestCategoryDelete))

        val resDelete = MpResponseCategoryDelete(
            category = MpCategoryDto(
                id = "Test for Delete response",
            )
        )
        val resDeleteStr = json.encodeToString(MpMessage.serializer(), resDelete)
        assertTrue{ resDeleteStr.contains("Test for Delete response")}
        val dtoResDelete = json.decodeFromString(MpMessage.serializer(), resDeleteStr)
        assertEquals(resDelete, (dtoResDelete as? MpResponseCategoryDelete))
    }
    //
    // проверка List Category
    // generated 16.03.2021 11:37:37
    //
    @Test
    fun test_s12n_Category_List() {
        val json = prepareJson()

        val reqList = MpRequestCategoryList(
            filterData = MpCategoryListFilterDto(
                type = "Test for List request",
            )
        )
        val reqListStr = json.encodeToString(MpMessage.serializer(), reqList)
        assertTrue{ reqListStr.contains("Test for List request")}
        val dtoReqList = json.decodeFromString(MpMessage.serializer(), reqListStr)
        assertEquals(reqList, (dtoReqList as? MpRequestCategoryList))

        val resList = MpResponseCategoryList(
            categories = listOf(MpCategoryDto(
                id = "Test for List response",
            ))
        )
        val resListStr = json.encodeToString(MpMessage.serializer(), resList)
        assertTrue{ resListStr.contains("Test for List response")}
        val dtoResList = json.decodeFromString(MpMessage.serializer(), resListStr)
        assertEquals(resList, (dtoResList as? MpResponseCategoryList))
    }

}
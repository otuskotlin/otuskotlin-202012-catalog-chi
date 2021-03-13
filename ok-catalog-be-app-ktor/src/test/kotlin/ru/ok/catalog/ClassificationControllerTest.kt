package ru.ok.catalog

import io.ktor.http.*
import io.ktor.server.testing.*
import ru.ok.catalog.be.common.models.CategoryType
import ru.ok.catalog.transport.kmp.models.category.*
import ru.ok.catalog.transport.kmp.models.classification.*
import ru.ok.catalog.transport.kmp.models.common.ResponseStatusDto
import ru.ok.catalog.transport.kmp.models.common.MpMessage
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.fail

class ClassificationControllerTest {

    @Test
    fun `Classification Create Test`() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Post, "/classification/create") {
                val body = MpRequestClassificationCreate(
                    requestId = "req-13",
                    createData = MpClassificationCreateDto(
                        productId = "prd-17",
                        categoryId = "cat-50"
                    )
                )
                val bodyString: String = jsonConfig.encodeToString(MpMessage.serializer(),body)
                println(bodyString)
                setBody(bodyString)
                addHeader("Content-Type","application/json")
            }.apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals(ContentType.Application.Json.withCharset(Charsets.UTF_8), response.contentType())
                val jsonString = response.content ?: fail("Null response json")
                println(jsonString)

                val res = (jsonConfig.decodeFromString(MpMessage.serializer(), jsonString) as? MpResponseClassificationCreate)
                    ?: fail("Incorrect response format")

                assertEquals(ResponseStatusDto.SUCCESS, res.status)
                assertEquals("req-13", res.onRequestId)
                assertEquals("prd-17", res.classification?.productId)
                assertEquals("cat-50", res.classification?.categoryId)
            }
        }
    }

    @Test
    fun `Classification Delete Test`() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Post, "/classification/delete") {    //TBD
                val body = MpRequestClassificationDelete(   //TBD
                    requestId = "req-13",
                    classificationId = "cls-22"
                )
                val bodyString: String = jsonConfig.encodeToString(MpMessage.serializer(),body)
                println(bodyString)
                setBody(bodyString)
                addHeader("Content-Type","application/json")
            }.apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals(ContentType.Application.Json.withCharset(Charsets.UTF_8), response.contentType())
                val jsonString = response.content ?: fail("Null response json")
                println(jsonString)

                //TBD
                val res = (jsonConfig.decodeFromString(MpMessage.serializer(), jsonString) as? MpResponseClassificationDelete)
                    ?: fail("Incorrect response format")

                assertEquals(ResponseStatusDto.SUCCESS, res.status)
                assertEquals("req-13", res.onRequestId)
                assertEquals("cls-22", res.classification?.id)
                assertEquals("prd-17", res.classification?.productId)
                assertEquals("cat-50", res.classification?.categoryId)
            }
        }
    }

    @Test
    fun `Classification List Test`() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Post, "/classification/list") {    //TBD
                val body = MpRequestClassificationList(     //TBD
                    requestId = "req-13",
                    filterData = MpClassificationListFilterDto(
                        productId = "prd-17"
                    )
                )
                val bodyString: String = jsonConfig.encodeToString(MpMessage.serializer(),body)
                println(bodyString)
                setBody(bodyString)
                addHeader("Content-Type","application/json")
            }.apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals(ContentType.Application.Json.withCharset(Charsets.UTF_8), response.contentType())
                val jsonString = response.content ?: fail("Null response json")
                println(jsonString)

                //TBD
                val res = (jsonConfig.decodeFromString(MpMessage.serializer(), jsonString) as? MpResponseClassificationList)
                    ?: fail("Incorrect response format")

                assertEquals(ResponseStatusDto.SUCCESS, res.status)
                assertEquals("req-13", res.onRequestId)
                assertEquals("prd-17", res.classifications?.first()?.productId)
                assertEquals("cat-57", res.classifications?.first()?.categoryId)
                assertEquals(2, res.classifications?.first()?.classificationPath?.count())
            }
        }
    }
}
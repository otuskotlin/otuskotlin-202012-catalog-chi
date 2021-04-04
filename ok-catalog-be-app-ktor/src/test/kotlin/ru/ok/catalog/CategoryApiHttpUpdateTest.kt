package ru.ok.catalog

import io.ktor.http.*
import io.ktor.server.testing.*
import ru.ok.catalog.be.common.models.CategoryType
import ru.ok.catalog.transport.kmp.models.category.*
import ru.ok.catalog.transport.kmp.models.common.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.fail

internal class CategoryApiHttpUpdateTest {

    @Test
    fun `ok`() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Post, "/category/update") {    //TBD
                val body = MpRequestCategoryUpdate(     //TBD
                    requestId = "req-13",
                    debug = MpRequestCategoryUpdate.Debug(
                        mode = MpWorkModeDto.STUB,
                        stubCase = "SUCCESS"
                    ),
                    updateData = MpCategoryUpdateDto(
                        type = "MARKETPLACE",
                        id = "cat-49",
                        code = "06.20.1",
                        title = "Добыча природного газа+",
                        upRefId = "cat-50"
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
                val res = (jsonConfig.decodeFromString(MpMessage.serializer(), jsonString) as? MpResponseCategoryUpdate)
                    ?: fail("Incorrect response format")

                assertEquals(ResponseStatusDto.SUCCESS, res.status)
                assertEquals("req-13", res.onRequestId)
                assertEquals("06.20.1", res.category?.code)
                assertEquals("cat-50", res.category?.upRefId)
                assertEquals("Добыча природного газа+", res.category?.title)
            }
        }
    }

    @Test
    fun `err exception`() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Post, "/category/update") {    //TBD
                val body = MpRequestCategoryUpdate(     //TBD
                    requestId = "req-13",
                    debug = MpRequestCategoryUpdate.Debug(
                        mode = MpWorkModeDto.STUB,
                        stubCase = "EXCEPTION"
                    ),
                    updateData = MpCategoryUpdateDto(
                        type = "MARKETPLACE",
                        id = "cat-49",
                        code = "06.20.1",
                        title = "Добыча природного газа+",
                        upRefId = "cat-50"
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
                val res = (jsonConfig.decodeFromString(MpMessage.serializer(), jsonString) as? MpResponseCategoryUpdate)
                    ?: fail("Incorrect response format")

                assertEquals(ResponseStatusDto.FAIL, res.status)
            }
        }
    }

    @Test
    fun `err error`() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Post, "/category/update") {    //TBD
                val body = MpRequestCategoryUpdate(     //TBD
                    requestId = "req-13",
                    debug = MpRequestCategoryUpdate.Debug(
                        mode = MpWorkModeDto.STUB,
                        stubCase = "ERROR"
                    ),
                    updateData = MpCategoryUpdateDto(
                        type = "MARKETPLACE",
                        id = "cat-49",
                        code = "06.20.1",
                        title = "Добыча природного газа+",
                        upRefId = "cat-50"
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
                val res = (jsonConfig.decodeFromString(MpMessage.serializer(), jsonString) as? MpResponseCategoryUpdate)
                    ?: fail("Incorrect response format")

                assertEquals(ResponseStatusDto.ERROR, res.status)
            }
        }
    }
}
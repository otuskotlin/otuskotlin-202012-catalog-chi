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

internal class CategoryApiHttpReadTest {

    @Test
    fun `ok`() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Post, "/category/read") {
                val body = MpRequestCategoryRead(
                    requestId = "req-13",
                    debug = MpRequestCategoryRead.Debug(
                        mode = MpWorkModeDto.STUB,
                        stubCase = "SUCCESS"
                    ),
                    categoryId = "cat-57"
                )
                val bodyString: String = jsonConfig.encodeToString(MpMessage.serializer(),body)
                //println(bodyString)
                setBody(bodyString)
                addHeader("Content-Type","application/json")
            }.apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals(ContentType.Application.Json.withCharset(Charsets.UTF_8), response.contentType())
                val jsonString = response.content ?: fail("Null response json")
                println(jsonString)

                val res = (jsonConfig.decodeFromString(MpMessage.serializer(), jsonString) as? MpResponseCategoryRead)
                    ?: fail("Incorrect response format")

                assertEquals(ResponseStatusDto.SUCCESS, res.status)
                assertEquals("req-13", res.onRequestId)
                assertEquals("cat-57", res.category?.id)
            }
        }
    }

    @Test
    fun `err validation requestId`() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Post, "/category/read") {
                val body = MpRequestCategoryRead(
                    requestId = "req-13",
                    //categoryId = "cat-57"
                )
                val bodyString: String = jsonConfig.encodeToString(MpMessage.serializer(),body)
                //println(bodyString)
                setBody(bodyString)
                addHeader("Content-Type","application/json")
            }.apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals(ContentType.Application.Json.withCharset(Charsets.UTF_8), response.contentType())
                val jsonString = response.content ?: fail("Null response json")
                println(jsonString)

                val res = (jsonConfig.decodeFromString(MpMessage.serializer(), jsonString) as? MpResponseCategoryRead)
                    ?: fail("Incorrect response format")

                assertEquals(ResponseStatusDto.ERROR, res.status)
                assertEquals("req-13", res.onRequestId)
            }
        }
    }

    @Test
    fun `err validation stubCase`() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Post, "/category/read") {
                val body = MpRequestCategoryRead(
                    requestId = "req-13",
                    debug = MpRequestCategoryRead.Debug(
                        mode = MpWorkModeDto.STUB,
                        stubCase = "XXX"
                    ),
                )
                val bodyString: String = jsonConfig.encodeToString(MpMessage.serializer(),body)
                //println(bodyString)
                setBody(bodyString)
                addHeader("Content-Type","application/json")
            }.apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals(ContentType.Application.Json.withCharset(Charsets.UTF_8), response.contentType())
                val jsonString = response.content ?: fail("Null response json")
                println(jsonString)

                val res = (jsonConfig.decodeFromString(MpMessage.serializer(), jsonString) as? MpResponseCategoryRead)
                    ?: fail("Incorrect response format")

                assertEquals(ResponseStatusDto.ERROR, res.status)
                assertEquals("req-13", res.onRequestId)
            }
        }
    }

    @Test
    fun `err validation categoryId`() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Post, "/category/read") {
                val body = MpRequestCategoryRead(
                    requestId = "req-13",
                    //categoryId = "cat-57"
                )
                val bodyString: String = jsonConfig.encodeToString(MpMessage.serializer(),body)
                //println(bodyString)
                setBody(bodyString)
                addHeader("Content-Type","application/json")
            }.apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals(ContentType.Application.Json.withCharset(Charsets.UTF_8), response.contentType())
                val jsonString = response.content ?: fail("Null response json")
                println(jsonString)

                val res = (jsonConfig.decodeFromString(MpMessage.serializer(), jsonString) as? MpResponseCategoryRead)
                    ?: fail("Incorrect response format")

                assertEquals(ResponseStatusDto.ERROR, res.status)
                assertEquals("req-13", res.onRequestId)
            }
        }
    }

    @Test
    fun `err stub error`() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Post, "/category/read") {
                val body = MpRequestCategoryRead(
                    requestId = "req-13",
                    debug = MpRequestCategoryRead.Debug(
                        mode = MpWorkModeDto.STUB,
                        stubCase = "ERROR"
                    ),
                    categoryId = "cat-57"
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

                val res = (jsonConfig.decodeFromString(MpMessage.serializer(), jsonString) as? MpResponseCategoryRead)
                    ?: fail("Incorrect response format")

                assertEquals(ResponseStatusDto.ERROR, res.status)
                assertEquals("req-13", res.onRequestId)
                assertTrue(res.errors?.filter {
                    it.message == "Уou asked for error, you got it" && it.level == ErrorDto.ErrorLevelDto.ERROR
                }?.size == 1)
            }
        }
    }

    @Test
    fun `err stub exception`() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Post, "/category/read") {
                val body = MpRequestCategoryRead(
                    requestId = "req-13",
                    debug = MpRequestCategoryRead.Debug(
                        mode = MpWorkModeDto.STUB,
                        stubCase = "EXCEPTION"
                    ),
                    categoryId = "cat-57"
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

                val res = (jsonConfig.decodeFromString(MpMessage.serializer(), jsonString) as? MpResponseCategoryRead)
                    ?: fail("Incorrect response format")

                assertEquals(ResponseStatusDto.FAIL, res.status)
                assertEquals("req-13", res.onRequestId)
                assertTrue(res.errors?.filter {
                    it.message == "Уou asked for exception, you got it" && it.level == ErrorDto.ErrorLevelDto.ERROR
                }?.size == 1)
            }
        }
    }
}
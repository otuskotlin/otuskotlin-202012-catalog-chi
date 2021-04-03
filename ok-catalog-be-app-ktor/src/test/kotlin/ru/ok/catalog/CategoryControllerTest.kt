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

internal class CategoryControllerTest {

    @Test
    fun `Category Create Test`() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Post, "/category/create") {
                val body = MpRequestCategoryCreate(
                    requestId = "req-13",
                    debug = MpRequestCategoryCreate.Debug(
                        mode = MpWorkModeDto.STUB,
                        stubCase = "SUCCESS"
                    ),
                    createData = MpCategoryCreateDto(
                        type = CategoryType.PRODUCTION.toString(),
                        code = "06.20.1",
                        title = "Добыча природного газа",
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

                val res = (jsonConfig.decodeFromString(MpMessage.serializer(), jsonString) as? MpResponseCategoryCreate)
                    ?: fail("Incorrect response format")

                assertEquals(ResponseStatusDto.SUCCESS, res.status)
                assertEquals("req-13", res.onRequestId)
                assertEquals("06.20.1", res.category?.code)
                assertEquals("cat-50", res.category?.upRefId)
                assertEquals("Добыча природного газа", res.category?.title)
            }
        }
    }

    @Test
    fun `Category Read Test`() {
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
    fun `Category Read Test Validation Fail`() {
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
    fun `Category Read Test Error`() {
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
    fun `Category Read Test Exception`() {
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

    @Test
    fun `Category Update Test`() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Post, "/category/update") {    //TBD
                val body = MpRequestCategoryUpdate(     //TBD
                    requestId = "req-13",
                    updateData = MpCategoryUpdateDto(
                        type = CategoryType.MARKETPLACE.toString(),
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
    fun `Category Delete Test`() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Post, "/category/delete") {    //TBD
                val body = MpRequestCategoryDelete(   //TBD
                    requestId = "req-13",
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

                //TBD
                val res = (jsonConfig.decodeFromString(MpMessage.serializer(), jsonString) as? MpResponseCategoryDelete)
                    ?: fail("Incorrect response format")

                assertEquals(ResponseStatusDto.SUCCESS, res.status)
                assertEquals("req-13", res.onRequestId)
                assertEquals("cat-57", res.category?.id)
            }
        }
    }

    @Test
    fun `Category List Test`() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Post, "/category/list") {    //TBD
                val body = MpRequestCategoryList(     //TBD
                    requestId = "req-13",
                    debug = MpRequestCategoryList.Debug(
                        mode = MpWorkModeDto.STUB,
                        stubCase = "SUCCESS"
                    ),
                    filterData = MpCategoryListFilterDto(
                        type = "PRODUCTION",
                        parentId = "cat-50"
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
                val res = (jsonConfig.decodeFromString(MpMessage.serializer(), jsonString) as? MpResponseCategoryList)
                    ?: fail("Incorrect response format")

                assertEquals(ResponseStatusDto.SUCCESS, res.status)
                assertEquals("req-13", res.onRequestId)
                assertEquals(1,res.categories?.filter {
                    it!!.code == "28.41" && it.title == "Оборудование металлообрабатывающее"
                }?.size )
                assertEquals(1, res.categories?.filter {
                    it!!.code == "28.49" && it.title == "Станки прочие"
                }?.size )
                assertEquals("cat-50", res.categories?.first()?.upRefId)
            }
        }
    }
}
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

internal class CategoryApiHttpDeleteTest {

    @Test
    fun `ok`() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Post, "/category/delete") {    //TBD
                val body = MpRequestCategoryDelete(   //TBD
                    requestId = "req-13",
                    debug = MpRequestCategoryDelete.Debug(
                        mode = MpWorkModeDto.STUB,
                        stubCase = "SUCCESS"
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
    fun `err`() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Post, "/category/delete") {    //TBD
                val body = MpRequestCategoryDelete(   //TBD
                    requestId = "req-13",
                    debug = MpRequestCategoryDelete.Debug(
                        mode = MpWorkModeDto.STUB,
                        stubCase = "ERROR",
                        stubParams = mapOf<String,String>(
                            "message" to "some error from fe",
                            "code" to "E-02"
                        ),
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

                //TBD
                val res = (jsonConfig.decodeFromString(MpMessage.serializer(), jsonString) as? MpResponseCategoryDelete)
                    ?: fail("Incorrect response format")

                assertEquals(ResponseStatusDto.ERROR, res.status)
                assertEquals(1, res.errors?.filter {
                    it.message =="some error from fe" && it.code == "E-02"
                }?.size)
            }
        }
    }
}
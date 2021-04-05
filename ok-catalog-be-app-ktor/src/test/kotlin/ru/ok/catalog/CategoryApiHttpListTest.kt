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

internal class CategoryApiHttpListTest {

    @Test
    fun `ok`() {
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

    @Test
    fun `err validation type`() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Post, "/category/list") {    //TBD
                val body = MpRequestCategoryList(     //TBD
                    requestId = "req-13",
                    debug = MpRequestCategoryList.Debug(
                        mode = MpWorkModeDto.STUB,
                        stubCase = "SUCCESS"
                    ),
                    filterData = MpCategoryListFilterDto(
                        type = "XXX",
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

                assertEquals("req-13", res.onRequestId)
                assertEquals(ResponseStatusDto.ERROR, res.status)
                assertTrue(res.errors!!.isNotEmpty())
                assertEquals(1,res.errors?.filter {
                    it.message!!.contains("Недопустимое значение") && it.field=="type"
                }?.size )
            }
        }
    }
}
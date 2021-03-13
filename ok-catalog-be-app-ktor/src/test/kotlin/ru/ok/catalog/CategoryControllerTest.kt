package ru.ok.catalog

import io.ktor.http.*
import io.ktor.server.testing.*
import io.ktor.server.testing.TestApplicationRequest
import ru.ok.catalog.transport.kmp.models.category.MpRequestCategoryRead
import ru.ok.catalog.transport.kmp.models.category.MpResponseCategoryRead
import ru.ok.catalog.transport.kmp.models.common.EResponseStatusDto
import ru.ok.catalog.transport.kmp.models.common.MpMessage
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.fail

class CategoryControllerTest {

    @Test
    fun `Category Read Test`() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Post, "/category/read") {
                val body = MpRequestCategoryRead(
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

                val res = (jsonConfig.decodeFromString(MpMessage.serializer(), jsonString) as? MpResponseCategoryRead)
                    ?: fail("Incorrect response format")

                assertEquals(EResponseStatusDto.SUCCESS, res.status)
                assertEquals("req-13", res.onRequestId)
                assertEquals("cat-57", res.category?.id)
            }
        }
    }

}
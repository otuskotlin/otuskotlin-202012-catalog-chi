package ru.ok.catalog.business.logic.backend;

import kotlin.test.Test;
import ru.ok.catalog.be.common.context.MpBeContext;
import ru.ok.catalog.be.common.context.MpBeContextStatus
import ru.ok.catalog.be.common.models.CategoryType
import ru.ok.catalog.be.common.models.MpCategoryIdModel
import ru.ok.catalog.be.common.models.MpStubCase
import ru.ok.catalog.transport.kmp.models.category.MpCategoryListFilter
import runBlockingTest
import kotlin.test.assertEquals

class CategoryTiApiTest {
    @Test
    fun list() {
        var api = CategoryTiApi()
        var ctx = MpBeContext(
            requestId = "req-13",
            qryCategoryFilter = MpCategoryListFilter(type = CategoryType.MARKETPLACE, parentId = "test"),
            stubCase = MpStubCase.CATEGORY_LIST_SUCCESS
        )
        runBlockingTest {
            api.list(ctx)
        }
        println(ctx.resCategories)
        assertEquals(MpBeContextStatus.SUCCESS, ctx.status)
        assertEquals(2, ctx.resCategories.size)
        with(ctx.resCategories[0]) {
            assertEquals("test", upRefId.id)
        }
    }

    @Test
    fun read() {
        var api = CategoryTiApi()
        var ctx = MpBeContext(
            requestId = "req-13",
            qryCategoryId = MpCategoryIdModel("test"),
            stubCase = MpStubCase.CATEGORY_READ_SUCCESS
        )
        runBlockingTest {
            api.read(ctx)
        }
        println(ctx.resCategory)
        assertEquals(MpBeContextStatus.SUCCESS, ctx.status)
        with(ctx.resCategory) {
            assertEquals("Машиностроение", title)
        }
    }

    @Test
    fun `read ivalid stubcase` () {
        var api = CategoryTiApi()
        var ctx = MpBeContext(
            requestId = "req-13",
            qryCategoryId = MpCategoryIdModel("test"),
            stubCase = MpStubCase.INVALID
        )
        runBlockingTest {
            api.read(ctx)
        }
        println(ctx.errors)
        assertEquals(MpBeContextStatus.ERROR, ctx.status)
        assertEquals("Invalid stub case", ctx.errors[0].message)
    }

    @Test
    fun `read validation`() {
        var api = CategoryTiApi()
        var ctx = MpBeContext(
            requestId = "req-13",
            qryCategoryId = MpCategoryIdModel(""),
        )
        runBlockingTest {
            api.read(ctx)
        }
        println(ctx.errors)
        assertEquals(MpBeContextStatus.ERROR, ctx.status)
        assertEquals("Value must not be empty or null", ctx.errors[0].message)
    }
}

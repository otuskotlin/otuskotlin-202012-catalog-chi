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

class CategoryLogicTest {
    @Test
    fun filter() {
        var givenCrud = CategoryLogic()
        var givenContext = MpBeContext(
            requestCategoryFilter = MpCategoryListFilter(type = CategoryType.MARKETPLACE, parentId = "test"),
            stubCase = MpStubCase.CATEGORY_FILTER_SUCCESS
        )
        runBlockingTest {
            givenCrud.filter(givenContext)
        }
        assertEquals(MpBeContextStatus.SUCCESS, givenContext.status)
        assertEquals(1, givenContext.responseCategorys.size)
        with(givenContext.responseCategorys[0]) {
            assertEquals("test", upRefId.id)
        }
    }

    @Test
    fun read() {
        var givenCrud = CategoryLogic()
        var givenContext = MpBeContext(
            requestCategoryId = MpCategoryIdModel("test"),
            stubCase = MpStubCase.CATEGORY_READ_SUCCESS
        )
        runBlockingTest {
            givenCrud.read(givenContext)
        }
        assertEquals(MpBeContextStatus.SUCCESS, givenContext.status)
        with(givenContext.responseCategory) {
            assertEquals("read-ok", title)
        }
    }
}

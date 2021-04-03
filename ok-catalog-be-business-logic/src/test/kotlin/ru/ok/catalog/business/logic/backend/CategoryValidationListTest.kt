package ru.ok.catalog.business.logic.backend

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import ru.ok.catalog.be.common.context.MpBeContext
import ru.ok.catalog.be.common.models.CategoryType
import ru.ok.catalog.be.common.models.MpCategoryIdModel
import ru.ok.catalog.business.logic.backend.pipelines.CategoryListPipeline
import ru.ok.catalog.business.logic.backend.pipelines.CategoryReadPipeline
import ru.ok.catalog.transport.kmp.models.category.MpCategoryListFilter
import kotlin.test.assertEquals


internal class CategoryValidationListTest {

    @Test
    fun `err validation type`() {
        val ctx = MpBeContext(
            qryCategoryFilter = MpCategoryListFilter(type = CategoryType.INVALID)
        )
        runBlocking {
            CategoryListPipeline.execute(ctx)
            println(ctx.errors)
            assertEquals(1,ctx.errors.filter {
                it.message.contains("Значение type не должно быть из списка INVALID")
            }.size )
        }
    }
}
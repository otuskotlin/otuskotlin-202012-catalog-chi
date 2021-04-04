package ru.ok.catalog.business.logic.backend

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import ru.ok.catalog.be.common.context.MpBeContext
import ru.ok.catalog.be.common.models.CategoryType
import ru.ok.catalog.be.common.models.MpCategoryIdModel
import ru.ok.catalog.be.common.models.MpCategoryModel
import ru.ok.catalog.business.logic.backend.pipelines.CategoryCreatePipeline
import ru.ok.catalog.business.logic.backend.pipelines.CategoryListPipeline
import ru.ok.catalog.business.logic.backend.pipelines.CategoryReadPipeline
import ru.ok.catalog.business.logic.backend.pipelines.CategoryUpdatePipeline
import ru.ok.catalog.transport.kmp.models.category.MpCategoryListFilter
import kotlin.test.assertEquals


internal class CategoryValidationUpdateTest {

    @Test
    fun `err validation`() {
        val ctx = MpBeContext(
            qryCategory = MpCategoryModel(
                id = MpCategoryIdModel.NONE,
                type = CategoryType.INVALID,
                code = "28.41",
                title = "",
                upRefId = MpCategoryIdModel.NONE
            )
        )
        runBlocking {
            CategoryUpdatePipeline.execute(ctx)
            println(ctx.errors)
            assertEquals(1,ctx.errors.filter {
                it.message.contains("Значение type не должно быть из списка INVALID")
            }.size )
            assertEquals(1,ctx.errors.filter {
                it.message.contains("Значение title не должно быть пустым")
            }.size )
            assertEquals(1,ctx.errors.filter {
                it.message.contains("Значение categoryId не должно быть пустым")
            }.size )
        }
    }
}
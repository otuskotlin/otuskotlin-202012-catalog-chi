package ru.ok.catalog.business.logic.backend

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import ru.ok.catalog.be.common.context.MpBeContext
import ru.ok.catalog.be.common.models.MpCategoryIdModel
import ru.ok.catalog.business.logic.backend.pipelines.CategoryReadPipeline
import kotlin.test.assertEquals


internal class CategoryValidationReadTest {

    @Test
    fun `err validation categoryId`() {
        val ctx = MpBeContext(
            qryCategoryId = MpCategoryIdModel.NONE
        )
        runBlocking {
            CategoryReadPipeline.execute(ctx)
            println(ctx.errors)
            assertEquals(1,ctx.errors.filter {
                it.message.contains("Значение categoryId не должно быть пустым")
            }.size )
        }
    }
}
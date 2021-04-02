package ru.ok.catalog.business.logic.backend

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import ru.ok.catalog.be.common.context.MpBeContext
import ru.ok.catalog.be.common.models.MpCategoryIdModel
import ru.ok.catalog.business.logic.backend.pipelines.CategoryReadPipeline
import kotlin.test.assertEquals


internal class CategoryReadValidation {

    @Test
    fun `fail on catigoryId empty`() {
        val ctx = MpBeContext(
            qryCategoryId = MpCategoryIdModel.NONE
        )
        runBlocking {
            CategoryReadPipeline.execute(ctx)
            println(ctx)
            //TODO здесь нужно проверять конкретное сообщение об ошибке
            //но findOrNull, показанный в коде на лекции не существует, вернемся к этом позже
//            assertTrue {
//                ctx.errors.findOrNull {
//                    it.message.contains("empty")
//                } != null
//            }
            assertEquals(1,ctx.errors.size)
        }
    }
}
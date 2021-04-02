package ru.ok.catalog.business.logic.backend

import ru.ok.catalog.be.common.context.MpBeContext
import ru.ok.catalog.business.logic.backend.pipelines.CategoryListPipeline
import ru.ok.catalog.business.logic.backend.pipelines.CategoryReadPipeline

/**
 * Transport Independent Category API
 **/
class CategoryTiApi {
    suspend fun list(context: MpBeContext) {
        CategoryListPipeline.execute(context)
    }
    suspend fun create(context: MpBeContext) {}
    suspend fun read(context: MpBeContext) {
        CategoryReadPipeline.execute(context)
    }
    suspend fun update(context: MpBeContext) {}
    suspend fun delete(context: MpBeContext) {}
}
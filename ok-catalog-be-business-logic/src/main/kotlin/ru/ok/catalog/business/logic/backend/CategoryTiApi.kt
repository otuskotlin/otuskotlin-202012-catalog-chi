package ru.ok.catalog.business.logic.backend

import ru.ok.catalog.be.common.context.MpBeContext
import ru.ok.catalog.business.logic.backend.pipelines.*

/**
 * Transport Independent Category API
 **/
class CategoryTiApi {
    suspend fun list(context: MpBeContext) {
        CategoryListPipeline.execute(context)
    }
    suspend fun create(context: MpBeContext) {
        CategoryCreatePipeline.execute(context)
    }
    suspend fun read(context: MpBeContext) {
        CategoryReadPipeline.execute(context)
    }
    suspend fun update(context: MpBeContext) {
        CategoryUpdatePipeline.execute(context)
    }
    suspend fun delete(context: MpBeContext) {
        CategoryDeletePipeline.execute(context)
    }
}
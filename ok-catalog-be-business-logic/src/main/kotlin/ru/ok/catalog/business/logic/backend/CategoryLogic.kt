package ru.ok.catalog.business.logic.backend

import ru.ok.catalog.be.common.context.MpBeContext
import ru.ok.catalog.business.logic.backend.pipelines.CategoryFilterPipeline
import ru.ok.catalog.business.logic.backend.pipelines.CategoryReadPipeline

//TODO переименовать, сейчас название не соотвествует сути
class CategoryLogic {
    suspend fun filter(context: MpBeContext) {
        CategoryFilterPipeline.execute(context)
    }
    suspend fun create(context: MpBeContext) {}
    suspend fun read(context: MpBeContext) {
        CategoryReadPipeline.execute(context)
    }
    suspend fun update(context: MpBeContext) {}
    suspend fun delete(context: MpBeContext) {}
}
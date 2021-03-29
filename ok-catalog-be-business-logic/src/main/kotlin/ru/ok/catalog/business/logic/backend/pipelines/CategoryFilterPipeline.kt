package ru.ok.catalog.business.logic.backend.pipelines

import ru.ok.catalog.be.common.context.MpBeContext
import ru.ok.catalog.be.common.context.MpBeContextStatus
import ru.ok.catalog.be.common.models.MpCategoryIdModel
import ru.ok.catalog.be.common.models.MpCategoryModel
import ru.ok.catalog.be.common.models.MpStubCase
import ru.ok.catalog.business.logic.backend.operations.CategoryFilterStub
import ru.ok.catalog.business.logic.backend.operations.PipelineFinalizeOperation
import ru.ok.catalog.business.logic.backend.operations.PipelineInitOperation
import ru.ok.catalog.pipelines.kmp.IOperation
import ru.ok.catalog.pipelines.kmp.operation
import ru.ok.catalog.pipelines.kmp.pipeline

object CategoryFilterPipeline: IOperation<MpBeContext> by pipeline<MpBeContext>( {
    execute(PipelineInitOperation)

    //валидация

    //заглушка
    execute(CategoryFilterStub)

    execute(PipelineFinalizeOperation)

    //запрос к БД
})
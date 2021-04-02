package ru.ok.catalog.business.logic.backend.pipelines

import ru.ok.catalog.be.common.context.MpBeContext
import ru.ok.catalog.business.logic.backend.operations.CategoryListStub
import ru.ok.catalog.business.logic.backend.operations.PipelineFinalizeOperation
import ru.ok.catalog.business.logic.backend.operations.PipelineInitOperation
import ru.ok.catalog.kmp.pipeline.IOperation
import ru.ok.catalog.kmp.pipeline.pipeline

object CategoryListPipeline: IOperation<MpBeContext> by pipeline<MpBeContext>( {
    //инициализация
    execute(PipelineInitOperation)

    //заглушка
    execute(CategoryListStub)

    //валидация

    //запрос к БД

    //финализация
    execute(PipelineFinalizeOperation)
})
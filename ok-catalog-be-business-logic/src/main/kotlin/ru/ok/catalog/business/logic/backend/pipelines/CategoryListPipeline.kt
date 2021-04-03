package ru.ok.catalog.business.logic.backend.pipelines

import ru.ok.catalog.be.common.context.MpBeContext
import ru.ok.catalog.business.logic.backend.helpers.validation
import ru.ok.catalog.business.logic.backend.operations.CategoryListStub
import ru.ok.catalog.business.logic.backend.operations.PipelineFinalizeOperation
import ru.ok.catalog.business.logic.backend.operations.PipelineInitOperation
import ru.ok.catalog.common.mp.validation.ValidatorStringNotEmpty
import ru.ok.catalog.common.mp.validation.ValidatorStringNotInSet
import ru.ok.catalog.kmp.pipeline.IOperation
import ru.ok.catalog.kmp.pipeline.pipeline
import ru.ok.catalog.common.mp.validation.validators.*
import ru.ok.catalog.kmp.pipeline.validation.validation

object CategoryListPipeline: IOperation<MpBeContext> by pipeline<MpBeContext>( {
    //инициализация
    execute(PipelineInitOperation)

    //заглушка
    execute(CategoryListStub)

    //валидация
    validation {
        validate<String> {
            validator(ValidatorStringNotInSet( code="MP-E-0009", field = "type", prohibited = mutableSetOf("INVALID") ))
            on { qryCategoryFilter.type.toString()}
        }
    }

    //запрос к БД

    //финализация
    execute(PipelineFinalizeOperation)
})




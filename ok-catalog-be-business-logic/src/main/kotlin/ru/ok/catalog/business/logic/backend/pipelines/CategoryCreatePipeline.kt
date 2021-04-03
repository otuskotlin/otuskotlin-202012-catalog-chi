package ru.ok.catalog.business.logic.backend.pipelines

import ru.ok.catalog.be.common.context.MpBeContext
import ru.ok.catalog.business.logic.backend.helpers.validation
import ru.ok.catalog.business.logic.backend.operations.CategoryCreateStub
import ru.ok.catalog.business.logic.backend.operations.PipelineFinalizeOperation
import ru.ok.catalog.business.logic.backend.operations.PipelineInitOperation
import ru.ok.catalog.common.mp.validation.ValidatorStringNotEmpty
import ru.ok.catalog.common.mp.validation.ValidatorStringNotInSet
import ru.ok.catalog.kmp.pipeline.IOperation
import ru.ok.catalog.kmp.pipeline.pipeline
//import ru.ok.catalog.kmp.pipeline.validation.validation

//import ru.ok.catalog.kmp.pipeline.validation.validation

object CategoryCreatePipeline: IOperation<MpBeContext> by pipeline ({
    execute(PipelineInitOperation)

    execute(CategoryCreateStub)

    //валидация
    validation {
        validate<String> {
            validator(ValidatorStringNotInSet( code="MP-E-0012", field = "type", prohibited = mutableSetOf("INVALID") ))
            on { qryCategory.type.toString()}
        }
        validate<String> {
            validator( ValidatorStringNotEmpty( code = "MP-E-0013", field = "title" ) )
            on { qryCategory.title}
        }
    }

    execute(PipelineFinalizeOperation)

})

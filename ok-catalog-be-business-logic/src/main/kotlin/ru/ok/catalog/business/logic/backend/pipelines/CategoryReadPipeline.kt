package ru.ok.catalog.business.logic.backend.pipelines

import ru.ok.catalog.be.common.context.MpBeContext
import ru.ok.catalog.business.logic.backend.operations.CategoryReadStub
import ru.ok.catalog.business.logic.backend.operations.PipelineFinalizeOperation
import ru.ok.catalog.business.logic.backend.operations.PipelineInitOperation
import ru.ok.catalog.common.mp.validation.validators.ValidatorStringNonEmpty
import ru.ok.catalog.kmp.pipeline.IOperation
import ru.ok.catalog.kmp.pipeline.pipeline
//import ru.ok.catalog.kmp.pipeline.validation.validation

//import ru.ok.catalog.kmp.pipeline.validation.validation
import ru.ok.catalog.business.logic.backend.helpers.validation
import ru.ok.catalog.common.mp.validation.ValidatorStringNotEmpty
import ru.ok.catalog.kmp.pipeline.validation.validation

object CategoryReadPipeline: IOperation<MpBeContext> by pipeline ({
    execute(PipelineInitOperation)

    execute(CategoryReadStub)

    validation {
        validate<String> {
            validator( ValidatorStringNotEmpty( code = "MP-E-0007", field = "categoryId" ) )
            on { qryCategoryId.id}
        }
    }

    execute(PipelineFinalizeOperation)
})

package ru.ok.catalog.business.logic.backend.pipelines

import ru.ok.catalog.be.common.context.MpBeContext
import ru.ok.catalog.business.logic.backend.operations.PipelineFinalizeOperation
import ru.ok.catalog.business.logic.backend.operations.PipelineInitOperation
import ru.ok.catalog.kmp.pipeline.IOperation
import ru.ok.catalog.kmp.pipeline.pipeline
import ru.ok.catalog.business.logic.backend.helpers.validation
import ru.ok.catalog.business.logic.backend.operations.CategoryDeleteStub
import ru.ok.catalog.common.mp.validation.ValidatorStringNotEmpty

object CategoryDeletePipeline: IOperation<MpBeContext> by pipeline ({
    execute(PipelineInitOperation)

    execute(CategoryDeleteStub)

    validation {
        validate<String> {
            validator( ValidatorStringNotEmpty( code = "MP-E-0020", field = "categoryId" ) )
            on { qryCategoryId.id}
        }
    }

    validation {
        validate<String> {
            validator( ValidatorStringNotEmpty( code = "MP-E-0034", field = "stub1" ) )
            on { "" }
        }
    }

    validation {
        validate<String> {
            validator( ValidatorStringNotEmpty( code = "MP-E-0035", field = "stub2" ) )
            on { "" }
        }
    }

    execute(PipelineFinalizeOperation)
})

package ru.ok.catalog.business.logic.backend.pipelines

import ru.ok.catalog.be.common.context.IMpError
import ru.ok.catalog.be.common.context.MpBeContext
import ru.ok.catalog.be.common.context.MpBeContextStatus
import ru.ok.catalog.business.logic.backend.operations.CategoryReadStub
import ru.ok.catalog.business.logic.backend.operations.PipelineFinalizeOperation
import ru.ok.catalog.business.logic.backend.operations.PipelineInitOperation
import ru.ok.catalog.common.mp.validation.ValidationFieldError
import ru.ok.catalog.common.mp.validation.ValidationResult
import ru.ok.catalog.common.mp.validation.ValidatorStringNotEmpty
import ru.ok.catalog.kmp.pipeline.IOperation
import ru.ok.catalog.kmp.pipeline.pipeline
//import ru.ok.catalog.kmp.pipeline.validation.validation

//import ru.ok.catalog.kmp.pipeline.validation.validation
import ru.ok.catalog.business.logic.backend.helpers.validation

object CategoryReadPipeline: IOperation<MpBeContext> by pipeline ({
    execute(PipelineInitOperation)

    //!!! стаб должен отработать без валидации, тому, кто его запросил,
    //!!! нужен результат запрошеннго кейса, валидация ему не нужна
    execute(CategoryReadStub)

    validation {
        validate<String> {
            validator(ValidatorStringNotEmpty(
                field = "categoryId"
            ))
            on { qryCategoryId.id}
        }
    }

    execute(PipelineFinalizeOperation)

})

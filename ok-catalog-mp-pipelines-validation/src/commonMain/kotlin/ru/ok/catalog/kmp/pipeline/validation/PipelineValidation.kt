package ru.ok.catalog.kmp.pipeline.validation

import ru.ok.catalog.kmp.pipeline.IOperation

class PipelineValidation<C>(
    private val validations: List<IValidationOperation<C, *>>
) : IOperation<C> {
    override suspend fun execute(context: C) {
        validations.forEach {
            it.execute(context)
        }
    }

}
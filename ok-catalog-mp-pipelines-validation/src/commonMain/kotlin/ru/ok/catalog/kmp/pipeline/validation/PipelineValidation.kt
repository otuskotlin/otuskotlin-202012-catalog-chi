package ru.ok.catalog.kmp.pipeline.validation

import ru.ok.catalog.kmp.pipeline.IOperation
import ru.ok.catalog.kmp.pipeline.Predicate

class PipelineValidation<C>(
    private val validations: List<IValidationOperation<C,*>>,
    private val checkPrecondition: Predicate<C> = { true },
    ) : IOperation<C> {
    override suspend fun execute(context: C) {
        if (context.checkPrecondition()) {
            validations.forEach {
                it.execute(context)
            }
        }
    }
}

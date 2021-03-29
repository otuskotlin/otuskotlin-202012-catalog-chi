package ru.ok.catalog.pipelines.kmp

class PipelineValidation<C>(
    private val validations: List<IValidationOperation<C, *>>
) : IOperation<C> {
    override suspend fun execute(context: C) {
        validations.forEach {
            it.execute(context)
        }
    }

}
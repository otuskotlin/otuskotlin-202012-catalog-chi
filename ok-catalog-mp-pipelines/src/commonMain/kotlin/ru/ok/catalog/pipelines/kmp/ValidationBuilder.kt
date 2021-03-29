package ru.ok.catalog.pipelines.kmp

import ru.ok.catalog.common.mp.validation.ValidationResult

class ValidationBuilder<C>: IOperationBuilder<C> {
    private var errorHandler: C.(ValidationResult) -> Unit = {}
    private val validators: MutableList<IValidationOperation<C, *>> = mutableListOf()

    fun errorHandler(block: C.(ValidationResult) -> Unit) {
        errorHandler = block
    }

    fun <T> validate(block: ValidationOperationBuilder<C, T>.() -> Unit) {
        val builder = ValidationOperationBuilder<C, T>(errorHandler).apply(block)
        validators.add(builder.build())
    }

    override fun build(): IOperation<C> = PipelineValidation(validators)

}
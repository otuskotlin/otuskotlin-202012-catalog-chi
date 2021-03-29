package ru.ok.catalog.pipelines.kmp

import ru.ok.catalog.common.mp.validation.IValidator
import ru.ok.catalog.common.mp.validation.ValidationResult

@PipelineDsl
// !!! В DSL, Builder - только для настроек
// !!! без Builder - для выполнения, работает в runtime
class ValidationOperationBuilder<C, T>(
    private var errorHandler: C.(ValidationResult) -> Unit = {}
) {
    private lateinit var onBlock: C.() -> T
    private lateinit var validator: IValidator<T>
    fun validator(validator: IValidator<T>) {
        this.validator = validator
    }
    fun on(block: C.() -> T) {
        onBlock = block
    }
    fun build(): IValidationOperation<C, T> {
        return DefaultValidationOperation(
            validator = validator,
            onBlock = onBlock,
            errorHandler = errorHandler
        )
    }
}
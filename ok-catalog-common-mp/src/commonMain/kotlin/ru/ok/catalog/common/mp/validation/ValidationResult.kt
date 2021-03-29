package ru.ok.catalog.common.mp.validation

class ValidationResult(
    val errors: List<IValidationError>
    ){


    constructor(vararg errors: ValidationResult) : this(errors = errors.flatMap { it.errors }.toList())

        val isSuccess: Boolean
        get() = errors.isEmpty()

    companion object {
        val SUCCESS = ValidationResult(errors = emptyList())
    }
}
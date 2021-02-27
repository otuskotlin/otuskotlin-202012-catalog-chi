package ru.ok.catalog.common.mp.validation

interface IValidator<T> {
    fun validate(sample: T): ValidationResult
}


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

data class ValidationDefaultError(
    override val message: String
): IValidationError

data class ValidationFieldError(
    override val message: String,
    override val field: String
): IValidationFieldError

interface IValidationError {
    val message: String
}

interface IValidationFieldError: IValidationError {
    val field: String
}
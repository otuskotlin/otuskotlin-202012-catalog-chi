package ru.ok.catalog.common.mp.validation

data class Child(
    val age:Int,
    val name:String
)

class ValidatorChild: IValidator<Child> {
    val valAge = ValidatorInRange<Int>("age", 2, 5)
    val valName = ValidatorStringNotEmpty()

    override fun validate(sample: Child): ValidationResult = ValidationResult(
        valAge.validate(sample.age),
        valName.validate(sample.name)
    )
}

fun <T: Comparable<T>> T.validate(validator: ValidatorInRange<T>) = validator.validate(this)
class ValidatorInRange<T: Comparable<T>>(val field: String, val min: T, val max: T): IValidator<T> {
    override fun validate(sample: T): ValidationResult {
        if (sample in min..max) {
            return ValidationResult.SUCCESS
        } else {
            return ValidationResult(
                errors = listOf(
                    ValidationFieldError(
                        field = field,
                        message = "Field $field with value $sample must be in range [$min,$max]"
                    )
                )
            )
        }
    }
}

class ValidatorStringNotEmpty(
    private val field: String = "",
    private val message: String = "Value must not be empty or null"
): IValidator<String> {
    override fun validate(sample: String): ValidationResult {
        if ( sample.isNullOrBlank() ) {
            return ValidationResult(
                errors = listOf(
                    ValidationFieldError(
                        message = message,
                        field = field
                    )
                )
            )
        } else {
            return ValidationResult.SUCCESS
        }
    }
}

fun String.validate(validator: ValidatorStringNotEmpty) = validator.validate(this)
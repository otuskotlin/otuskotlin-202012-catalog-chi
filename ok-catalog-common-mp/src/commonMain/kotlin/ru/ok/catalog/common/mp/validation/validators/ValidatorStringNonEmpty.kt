package ru.ok.catalog.common.mp.validation.validators

import ru.ok.catalog.common.mp.validation.IValidator
import ru.ok.catalog.common.mp.validation.ValidationDefaultError
import ru.ok.catalog.common.mp.validation.ValidationResult

class ValidatorStringNonEmpty: IValidator<String?> {

    override fun validate(sample: String?): ValidationResult {
        return if (sample.isNullOrBlank()) {
            ValidationResult(
                errors = listOf(
                    ValidationDefaultError(
                        message = "String must not be empty",
                    )
                )
            )
        } else {
            ValidationResult.SUCCESS
        }
    }

}

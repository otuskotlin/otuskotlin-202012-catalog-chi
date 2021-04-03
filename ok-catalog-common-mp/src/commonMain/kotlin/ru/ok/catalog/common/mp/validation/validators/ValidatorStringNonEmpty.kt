package ru.ok.catalog.common.mp.validation.validators

import ru.ok.catalog.common.mp.validation.IValidator
import ru.ok.catalog.common.mp.validation.ValidationFieldError
import ru.ok.catalog.common.mp.validation.ValidationResult

class ValidatorStringNonEmpty(
    private val field: String = "",
    private val message: String = "",
    private val code: String = ""
): IValidator<String?> {

    override fun validate(sample: String?): ValidationResult {
        return if (sample.isNullOrBlank()) {
            ValidationResult(
                errors = listOf(
                    ValidationFieldError(
                        field = field,
                        message = if ( message == "" )
                                      "Значение $field не должно быть пустым"
                                  else
                                      message,
                        code = code
                    )
                )
            )
        } else {
            ValidationResult.SUCCESS
        }
    }

}

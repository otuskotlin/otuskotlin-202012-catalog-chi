package ru.ok.catalog.common.mp.validation

data class ValidationFieldError(
    override val message: String,
    override val field: String
): IValidationFieldError
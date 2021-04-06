package ru.ok.catalog.common.mp.validation


data class ValidationDefaultError(
    override val message: String,
    override val code: String
): IValidationError


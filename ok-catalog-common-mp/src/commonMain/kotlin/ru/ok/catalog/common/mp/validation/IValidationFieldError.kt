package ru.ok.catalog.common.mp.validation

interface IValidationFieldError: IValidationError {
    val field: String
    override val code: String
}
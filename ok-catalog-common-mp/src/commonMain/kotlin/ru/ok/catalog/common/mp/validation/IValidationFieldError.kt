package ru.ok.catalog.common.mp.validation

interface IValidationFieldError: IValidationError {
    val field: String
    val code: String
}
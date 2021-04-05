package ru.ok.catalog.common.mp.validation

interface IValidationError {
    val message: String
    val code: String
}
package ru.ok.catalog.common.mp.validation

interface IValidator<T> {
    fun validate(sample: T): ValidationResult
}
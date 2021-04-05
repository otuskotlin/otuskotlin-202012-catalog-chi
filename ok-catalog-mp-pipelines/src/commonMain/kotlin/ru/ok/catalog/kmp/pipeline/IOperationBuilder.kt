package ru.ok.catalog.kmp.pipeline

interface IOperationBuilder<T> {
    fun build(): IOperation<T>

}

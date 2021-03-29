package ru.ok.catalog.pipelines.kmp

interface IOperationBuilder<T> {
    fun build(): IOperation<T>

}

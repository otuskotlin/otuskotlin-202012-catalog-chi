package ru.ok.catalog.pipelines.kmp

interface IOperation<T> {
    suspend fun execute(context: T)
}
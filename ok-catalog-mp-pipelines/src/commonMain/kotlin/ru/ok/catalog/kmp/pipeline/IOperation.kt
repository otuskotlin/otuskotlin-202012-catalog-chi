package ru.ok.catalog.kmp.pipeline

interface IOperation<T> {
    suspend fun execute(context: T)
}
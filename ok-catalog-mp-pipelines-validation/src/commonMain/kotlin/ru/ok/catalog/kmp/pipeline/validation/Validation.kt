package ru.ok.catalog.kmp.pipeline.validation

import ru.ok.catalog.kmp.pipeline.Pipeline

fun <C> Pipeline.Builder<C>.validation(block: ValidationBuilder<C>.() -> Unit) {
    execute(ValidationBuilder<C>().apply(block).build())
}

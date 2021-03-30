package ru.ok.catalog.business.logic.backend.helpers

import ru.ok.catalog.be.common.context.IMpError
import ru.ok.catalog.be.common.context.MpBeContext
import ru.ok.catalog.be.common.context.MpBeContextStatus
import ru.ok.catalog.business.logic.backend.pipelines.MpError
import ru.ok.catalog.common.mp.validation.ValidationFieldError
import ru.ok.catalog.common.mp.validation.ValidationResult
import ru.ok.catalog.kmp.pipeline.Pipeline
import ru.ok.catalog.kmp.pipeline.validation.ValidationBuilder

fun Pipeline.Builder<MpBeContext>.validation(block: ValidationBuilder<MpBeContext>.() -> Unit) {
    execute(
        ValidationBuilder<MpBeContext>()
        .apply {
            startIf { status == MpBeContextStatus.RUNNING }
            errorHandler { vr: ValidationResult ->
                if (vr.isSuccess) return@errorHandler
                //TODO посмотри здесь на счет уровня
                val errs = vr.errors.map { MpError(
                    message = it.message,
                    field = when(it) {
                        is ValidationFieldError -> it.field
                        else -> ""
                    },
                    group = IMpError.Group.VALIDATION
                ) }
                errors.addAll(errs)
                status = MpBeContextStatus.FAILING
            }
        }
        .apply(block)
        .build())
}

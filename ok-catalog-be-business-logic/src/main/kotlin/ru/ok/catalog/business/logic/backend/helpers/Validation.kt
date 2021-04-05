package ru.ok.catalog.business.logic.backend.helpers

import ru.ok.catalog.be.common.context.IMpError
import ru.ok.catalog.be.common.context.MpBeContext
import ru.ok.catalog.be.common.context.MpBeContextStatus
import ru.ok.catalog.be.common.models.MpError
import ru.ok.catalog.common.mp.validation.ValidationFieldError
import ru.ok.catalog.common.mp.validation.ValidationResult
import ru.ok.catalog.kmp.pipeline.Pipeline
import ru.ok.catalog.kmp.pipeline.validation.ValidationBuilder

fun Pipeline.Builder<MpBeContext>.validation(block: ValidationBuilder<MpBeContext>.() -> Unit) {
    execute(ValidationBuilder<MpBeContext>()
        .apply {
            startIf { status == MpBeContextStatus.RUNNING }
            errorHandler { vr: ValidationResult ->
//                throw RuntimeException("Here33")

                if (vr.isSuccess) return@errorHandler
                //TODO посмотри здесь на счет уровня
                val errs = vr.errors.map {
                    MpError(
                        message = it.message,
                        level = IMpError.Level.ERROR,
                        field = when(it) {
                            is ValidationFieldError -> it.field
                            else -> ""
                        },
                        group = IMpError.Group.VALIDATION,
                        code = when(it) {
                            is ValidationFieldError -> it.code
                            else -> ""
                        }
                    )
                }
                errors.addAll(errs)
                status = MpBeContextStatus.FAILING
            }
        }
        .apply(block)
        .build())
}

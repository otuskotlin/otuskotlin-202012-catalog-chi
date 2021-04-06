package ru.ok.catalog.business.logic.backend.helpers

import ru.ok.catalog.be.common.context.IMpError
import ru.ok.catalog.be.common.context.MpBeContext
import ru.ok.catalog.be.common.context.MpBeContextStatus
import ru.ok.catalog.be.common.models.MpError
import ru.ok.catalog.business.logic.backend.validators.ErrorLevelService
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
                        level = ErrorLevelService.level(it.code),
                        field = when(it) {
                            is ValidationFieldError -> it.field
                            else -> ""
                        },
                        group = IMpError.Group.VALIDATION,
                        code = it.code,
                    )
                }
                errors.addAll(errs)
                errs.forEach {
                    if ( it.level.isError ) {
                        status = MpBeContextStatus.FAILING
                        return@forEach
                    }
                }
            }
        }
        .apply(block)
        .build())
}

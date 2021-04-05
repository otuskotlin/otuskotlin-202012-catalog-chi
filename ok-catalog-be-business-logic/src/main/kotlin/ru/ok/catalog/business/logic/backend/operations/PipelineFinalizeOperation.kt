package ru.ok.catalog.business.logic.backend.operations

import ru.ok.catalog.be.common.context.IMpError
import ru.ok.catalog.be.common.context.MpBeContext
import ru.ok.catalog.be.common.context.MpBeContextStatus
import ru.ok.catalog.kmp.pipeline.IOperation
import ru.ok.catalog.kmp.pipeline.operation
import ru.ok.catalog.kmp.pipeline.pipeline

object PipelineFinalizeOperation: IOperation<MpBeContext> by pipeline ({
    operation {
        startIf {
            status in setOf(MpBeContextStatus.RUNNING, MpBeContextStatus.FINISHING)
        }
        execute {
            //отфильтруем ошибки отключенных проверок
            errors = errors.filter { it.level != IMpError.Level.OFF }.toMutableList()
            status = MpBeContextStatus.SUCCESS
        }
    }
    operation {
        startIf {
            status != MpBeContextStatus.SUCCESS
        }
        execute {
            errors = errors.filter { it.level != IMpError.Level.OFF }.toMutableList()
            status = MpBeContextStatus.ERROR
        }
    }
})
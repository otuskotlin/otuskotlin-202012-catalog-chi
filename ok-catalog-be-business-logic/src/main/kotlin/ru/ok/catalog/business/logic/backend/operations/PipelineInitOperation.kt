package ru.ok.catalog.business.logic.backend.operations

import ru.ok.catalog.be.common.context.MpBeContext
import ru.ok.catalog.be.common.context.MpBeContextStatus
import ru.ok.catalog.pipelines.kmp.IOperation
import ru.ok.catalog.pipelines.kmp.operation

object PipelineInitOperation: IOperation<MpBeContext> by operation ({
    execute { status= MpBeContextStatus.RUNNING }
})


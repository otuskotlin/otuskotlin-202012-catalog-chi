package ru.ok.catalog.business.logic.backend.operations

import ru.ok.catalog.be.common.context.MpBeContext
import ru.ok.catalog.be.common.context.MpBeContextStatus
import ru.ok.catalog.kmp.pipeline.IOperation
import ru.ok.catalog.kmp.pipeline.operation

object PipelineInitOperation: IOperation<MpBeContext> by operation ({
    execute {
        if ( status == MpBeContextStatus.NONE ) {
            status = MpBeContextStatus.RUNNING
        }
    }
})


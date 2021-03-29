package ru.ok.catalog.business.logic.backend.operations

import ru.ok.catalog.be.common.context.MpBeContext
import ru.ok.catalog.be.common.context.MpBeContextStatus
import ru.ok.catalog.be.common.models.MpCategoryIdModel
import ru.ok.catalog.be.common.models.MpCategoryModel
import ru.ok.catalog.be.common.models.MpStubCase
import ru.ok.catalog.pipelines.kmp.IOperation
import ru.ok.catalog.pipelines.kmp.operation
import ru.ok.catalog.pipelines.kmp.pipeline

object CategoryFilterStub: IOperation<MpBeContext> by pipeline ({
    startIf { stubCase != MpStubCase.NONE }

    operation {
        startIf {
            stubCase == MpStubCase.CATEGORY_FILTER_SUCCESS
        }
        execute {
            responseCategorys.add(
                MpCategoryModel(
                    upRefId = MpCategoryIdModel(requestCategoryFilter.parentId)
                )
            )
            status = MpBeContextStatus.FINISHING
        }
    }

})
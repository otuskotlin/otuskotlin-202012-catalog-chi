package ru.ok.catalog.business.logic.backend.operations

import ru.ok.catalog.be.common.context.MpBeContext
import ru.ok.catalog.be.common.context.MpBeContextStatus
import ru.ok.catalog.be.common.models.MpCategoryIdModel
import ru.ok.catalog.be.common.models.MpCategoryModel
import ru.ok.catalog.be.common.models.MpStubCase
import ru.ok.catalog.pipelines.kmp.IOperation
import ru.ok.catalog.pipelines.kmp.operation
import ru.ok.catalog.pipelines.kmp.pipeline

object CategoryReadStub: IOperation<MpBeContext> by pipeline ({
    startIf { stubCase != MpStubCase.NONE }

    operation {
        startIf {
            stubCase == MpStubCase.CATEGORY_READ_SUCCESS
        }
        execute {
            responseCategory = MpCategoryModel(
                id = MpCategoryIdModel("cat-57"),
                title = "Машиностроение",
            )
            status = MpBeContextStatus.FINISHING
        }
    }

})
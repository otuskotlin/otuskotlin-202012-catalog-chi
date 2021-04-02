package ru.ok.catalog.business.logic.backend.operations

import ru.ok.catalog.be.common.context.MpBeContext
import ru.ok.catalog.be.common.context.MpBeContextStatus
import ru.ok.catalog.be.common.models.CategoryType
import ru.ok.catalog.be.common.models.MpCategoryIdModel
import ru.ok.catalog.be.common.models.MpCategoryModel
import ru.ok.catalog.be.common.models.MpStubCase
import ru.ok.catalog.kmp.pipeline.IOperation
import ru.ok.catalog.kmp.pipeline.operation
import ru.ok.catalog.kmp.pipeline.pipeline

object CategoryListStub: IOperation<MpBeContext> by pipeline ({
    startIf { stubCase != MpStubCase.NONE }

    operation {
        startIf {
            stubCase == MpStubCase.CATEGORY_LIST_SUCCESS
        }
        execute {
            resCategories.add(
                MpCategoryModel(
                    id = MpCategoryIdModel("cat-57"),
                    type = CategoryType.PRODUCTION,
                    code = "28.41",
                    title = "Оборудование металлообрабатывающее",
                    upRefId = MpCategoryIdModel(qryCategoryFilter.parentId),
                    isLeaf = true,
                )
            )
            resCategories.add(
                MpCategoryModel(
                    id = MpCategoryIdModel("cat-58"),
                    type = CategoryType.PRODUCTION,
                    code = "28.49",
                    title = "Станки прочие",
                    upRefId = MpCategoryIdModel(qryCategoryFilter.parentId),
                    isLeaf = true,
                )
            )
            status = MpBeContextStatus.FINISHING
        }
    }

})
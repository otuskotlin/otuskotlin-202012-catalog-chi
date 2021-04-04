package ru.ok.catalog.business.logic.backend.operations

import ru.ok.catalog.be.common.context.IMpError
import ru.ok.catalog.be.common.context.MpBeContext
import ru.ok.catalog.be.common.context.MpBeContextStatus
import ru.ok.catalog.be.common.models.MpCategoryIdModel
import ru.ok.catalog.be.common.models.MpCategoryModel
import ru.ok.catalog.be.common.models.MpStubCase
import ru.ok.catalog.be.common.models.MpError
import ru.ok.catalog.kmp.pipeline.IOperation
import ru.ok.catalog.kmp.pipeline.operation
import ru.ok.catalog.kmp.pipeline.pipeline

object CategoryReadStub: IOperation<MpBeContext> by pipeline ({
    startIf { stubCase != MpStubCase.NONE }

    //успешное чтение
    operation {
        startIf {
            stubCase == MpStubCase.CATEGORY_READ_SUCCESS
        }
        execute {
            resCategory = MpCategoryModel(
                id = MpCategoryIdModel(qryCategoryId.id),
                title = "Машиностроение",
            )
            status = MpBeContextStatus.FINISHING
        }
    }

    //ошибка
    operation {
        startIf {
            stubCase == MpStubCase.CATEGORY_READ_ERROR
        }
        execute {
            errors = mutableListOf(
                MpError(
                    code = "MP-E-0008",
                    message = "Уou asked for error, you got it",
                    level = IMpError.Level.ERROR,
                    field = "someField",
                    group = IMpError.Group.VALIDATION
                )
            )
            status = MpBeContextStatus.FAILING
        }
    }

    //исключение
    operation {
        startIf {
            stubCase == MpStubCase.CATEGORY_READ_EXCEPTION
        }
        execute {
            throw RuntimeException("MP-E-0024"+" Уou asked for exception, you got it")
        }
    }


})

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

object CategoryDeleteStub: IOperation<MpBeContext> by pipeline ({
    startIf { stubCase != MpStubCase.NONE }

    //успешное чтение
    operation {
        startIf {
            stubCase == MpStubCase.CATEGORY_DELETE_SUCCESS
        }
        execute {
            resCategory = MpCategoryModel(
                id = qryCategoryId,
                title = "Здесь будут реквизиты удаленной записи",
            )
            status = MpBeContextStatus.FINISHING
        }
    }

    //ошибка
    operation {
        startIf {
            stubCase == MpStubCase.CATEGORY_DELETE_ERROR
        }
        execute {
            errors = mutableListOf(
                MpError(
                    code = stubParams.getOrDefault("code", "MP-E-0021"),
                    message = stubParams.getOrDefault("message", "Уou asked for error, you got it"),
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
            stubCase == MpStubCase.CATEGORY_DELETE_EXCEPTION
        }
        execute {
            throw RuntimeException("MP-E-0022"+" Уou asked for exception, you got it")
        }
    }


})

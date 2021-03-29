package ru.ok.catalog.business.logic.backend.pipelines

import ru.ok.catalog.be.common.context.IMpError
import ru.ok.catalog.be.common.context.MpBeContext
import ru.ok.catalog.be.common.context.MpBeContextStatus
import ru.ok.catalog.be.common.models.MpCategoryIdModel
import ru.ok.catalog.be.common.models.MpCategoryModel
import ru.ok.catalog.be.common.models.MpStubCase
import ru.ok.catalog.business.logic.backend.operations.CategoryReadStub
import ru.ok.catalog.business.logic.backend.operations.PipelineFinalizeOperation
import ru.ok.catalog.business.logic.backend.operations.PipelineInitOperation
import ru.ok.catalog.common.mp.validation.ValidationFieldError
import ru.ok.catalog.common.mp.validation.ValidationResult
import ru.ok.catalog.common.mp.validation.ValidatorStringNotEmpty
import ru.ok.catalog.pipelines.kmp.IOperation
import ru.ok.catalog.pipelines.kmp.operation
import ru.ok.catalog.pipelines.kmp.pipeline

object CategoryReadPipeline: IOperation<MpBeContext> by pipeline ({
    execute(PipelineInitOperation)

    //!!! стаб должен отработать без валидации, тому, кто его запросил,
    //!!! нужен результат запрошеннго кейса, валидация ему не нужна
    execute(CategoryReadStub)

    //валидацию нужно выносить отсюда т.к. условия могут быть весьма развесистыми
    //и не стоит им расти здесь
    validation {
        errorHandler {v: ValidationResult ->
            if ( v.isSuccess ) return@errorHandler
            //!!! здесь нужен маппинг ошибок
            //!!! этого можно избежать, но при использованиии валидаторов
            //!!! сторонней разработки маппинг необходим
            val errs = v.errors.map{
                MpError(
                    //TODO mapping, без него не будет статуса ошибки
                    message = it.message,
                    level = IMpError.Level.ERROR,
                    field = when(it) {
                        is ValidationFieldError -> it.field
                        else -> ""
                    },
                    group = IMpError.Group.VALIDATION
                )
            }
            errs.forEach {
                errors.add(it)
            }

            status = MpBeContextStatus.FAILING

        }
        validate<String> {
            validator(ValidatorStringNotEmpty(
                field = "categoryId"
            ))
            on { requestCategoryId.id}
        }
    }

    // здесь предлагается сделать валидацию эксепшенов фреймворка,
    // но в моей проге все по простому: по успешному сценарию,
    // эксепшенов быть не должно либо они должны быть перехвачены
    // и корректно обработаны в ожидаемом месте, а
    // вдруг что-то нежданное возникнет, верну FE FAIL

    execute(PipelineFinalizeOperation)

})

package ru.ok.catalog.controllers

import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.util.pipeline.*
import org.slf4j.LoggerFactory
import ru.ok.catalog.be.common.FUID
import ru.ok.catalog.business.logic.backend.CategoryTiApi
import ru.ok.catalog.transport.kmp.models.category.*
import ru.ok.catalog.transport.kmp.models.common.ResponseStatusDto
import ru.ok.catalog.transport.kmp.models.common.ErrorDto
import ru.ok.catalog.transport.kmp.models.common.MpMessage
import java.time.Instant
import ru.ok.catalog.be.common.context.MpBeContext
import ru.ok.catalog.be.common.context.MpBeContextStatus
import ru.ok.catalog.be.mappers.check
import ru.ok.catalog.be.mappers.init
import ru.ok.catalog.be.mappers.preInit
import ru.ok.catalog.be.mappers.toDto

class MpCategoryApiHttpAdapter (
    private val tiApi: CategoryTiApi = CategoryTiApi()

    ){
    private val log = LoggerFactory.getLogger(this::class.java)!!

    suspend fun create(pipelineContext: PipelineContext<Unit, ApplicationCall>) {
        var requestId: String? = null
        try {
            //достать запрос в виде DTO
            val req = pipelineContext.call.receive<MpMessage>() as MpRequestCategoryCreate  //TBD
            requestId = req.requestId
            //преобразовать в контекст
            val ctx = MpBeContext().preInit(req).init(req).check(req)
            //вызвать pipeline
            if ( ctx.errors.size == 0 )
                tiApi.create(ctx)
            else
                ctx.status = MpBeContextStatus.ERROR
            //создать ответ с преобразованием данных в DTO
            val res: MpMessage = MpResponseCategoryCreate(  //TBD
                responseId = FUID.id(),
                onRequestId = requestId,
                endTime = Instant.now().toString(),
                status = ctx.status.toDto(),
                errors = ctx.errors.map {
                    it.toDto()
                }.ifEmpty { null },
                category = ctx.resCategory.toDto()
            )
            //отправить ответ
            pipelineContext.call.respond(res)
        } catch (e:Throwable) {
            log.error("Exception at MpCategoryApiHttpAdapter", e)
            val res: MpMessage = MpResponseCategoryCreate(  //TBD
                responseId = FUID.id() ,
                onRequestId = requestId,
                endTime = Instant.now().toString(),
                status = ResponseStatusDto.FAIL,
                errors = listOf(
                    ErrorDto(
                        message = e.message,
                        level = ErrorDto.ErrorLevelDto.ERROR,
                        code = "MP-E-0014"
                    )
                )
            )
            pipelineContext.call.respond(res)
        }
    }

    suspend fun read(pipelineContext: PipelineContext<Unit, ApplicationCall>)  {
        var requestId: String? = null
        try {
            //достать запрос в виде DTO
            val req = pipelineContext.call.receive<MpMessage>() as MpRequestCategoryRead  //TBD
            requestId = req.requestId
            //преобразовать в контекст
            val ctx = MpBeContext().preInit(req).init(req).check(req)
            if ( ctx.errors.size == 0 )
                tiApi.read(ctx)
            else
                ctx.status = MpBeContextStatus.ERROR
            //вызвать обработчик
            //создать ответ, представить данные в DTO
            val res: MpMessage = MpResponseCategoryRead(    //TBD
                responseId = FUID.id(),
                onRequestId = req.requestId,
                endTime = Instant.now().toString(),
                status = ctx.status.toDto(),
                errors = ctx.errors.map {
                    it.toDto()
                }.ifEmpty { null },
                category = ctx.resCategory.toDto(),
            )
            //отправить ответ
            pipelineContext.call.respond(res)
        } catch (e:Throwable) {
            log.error("Exception at MpCategoryApiHttpAdapter", e)
            val res: MpMessage = MpResponseCategoryRead(    //TBD
                responseId = FUID.id(),
                onRequestId = requestId,
                endTime = Instant.now().toString(),
                status = ResponseStatusDto.FAIL,
                errors = listOf(
                    ErrorDto(
                        message = e.message,
                        level = ErrorDto.ErrorLevelDto.ERROR,
                        code = "MP-E-0015"
                    )
                )
            )
            pipelineContext.call.respond(res)
        }
    }

    suspend fun update(pipelineContext: PipelineContext<Unit, ApplicationCall>) {
        var requestId: String? = null
        try {
            val req = pipelineContext.call.receive<MpMessage>() as MpRequestCategoryUpdate  //TBD
            requestId = req.requestId
            val ctx = MpBeContext().preInit(req).init(req).check(req)
            if ( ctx.errors.size == 0 )
                tiApi.update(ctx)
            else
                ctx.status = MpBeContextStatus.ERROR
            val res: MpMessage = MpResponseCategoryUpdate(  //TBD
                responseId = FUID.id(),
                onRequestId = requestId,
                endTime = Instant.now().toString(),
                status = ctx.status.toDto(),
                errors = ctx.errors.map {
                    it.toDto()
                }.ifEmpty { null },
                category = ctx.resCategory.toDto(),
            )
            //отправить ответ
            pipelineContext.call.respond(res)
        } catch (e:Throwable) {
            log.error("Exception at MpCategoryApiHttpAdapter", e)
            val res: MpMessage = MpResponseCategoryUpdate(  //TBD
                responseId = FUID.id() ,
                onRequestId = requestId,
                endTime = Instant.now().toString(),
                status = ResponseStatusDto.FAIL,
                errors = listOf(
                    ErrorDto(
                        message = e.message,
                        level = ErrorDto.ErrorLevelDto.ERROR,
                        code = "MP-E-0016"
                    )
                )
            )
            pipelineContext.call.respond(res)
        }
    }

    suspend fun delete(pipelineContext: PipelineContext<Unit, ApplicationCall>) {
        var requestId: String? = null
        try {
            val req = pipelineContext.call.receive<MpMessage>() as MpRequestCategoryDelete    //TBD
            requestId = req.requestId
            val ctx = MpBeContext().preInit(req).init(req).check(req).check(req)
            if ( ctx.errors.size == 0 )
                tiApi.delete(ctx)
            else
                ctx.status = MpBeContextStatus.ERROR
            val res: MpMessage = MpResponseCategoryDelete(    //TBD
                responseId = FUID.id(),
                onRequestId = req.requestId,
                endTime = Instant.now().toString(),
                status = ctx.status.toDto(),
                errors = ctx.errors.map {
                    it.toDto()
                }.ifEmpty { null },
                category = ctx.resCategory.toDto(),
            )

            pipelineContext.call.respond(res)
        } catch (e:Throwable) {
            log.error("Exception at MpCategoryApiHttpAdapter", e)
            val res: MpMessage = MpResponseCategoryDelete(    //TBD
                responseId = FUID.id(),
                onRequestId = requestId,
                endTime = Instant.now().toString(),
                status = ResponseStatusDto.FAIL,
                errors = listOf(
                    ErrorDto(
                        message = e.message,
                        level = ErrorDto.ErrorLevelDto.ERROR,
                        code = "MP-E-0017"
                    )
                )
            )
            pipelineContext.call.respond(res)
        }
    }

    suspend fun list(pipelineContext: PipelineContext<Unit, ApplicationCall>) {
        var requestId: String? = null
        try {
            val req = pipelineContext.call.receive<MpMessage>() as MpRequestCategoryList  //TBD
            requestId = req.requestId
            val ctx = MpBeContext().preInit(req).init(req).check(req)
            if ( ctx.errors.size == 0 )
                tiApi.list(ctx)
            else
                ctx.status = MpBeContextStatus.ERROR
            val res: MpMessage = MpResponseCategoryList(  //TBD
                responseId = FUID.id(),
                onRequestId = requestId,
                endTime = Instant.now().toString(),
                status = ctx.status.toDto(),
                errors = ctx.errors.map {
                    it.toDto()
                }.ifEmpty { null },
                categories = ctx.resCategories.map {
                    it.toDto()
                }.ifEmpty { null }
            )
            pipelineContext.call.respond(res)
        } catch (e:Throwable) {
            log.error("Exception at MpCategoryApiHttpAdapter", e)
            val res: MpMessage = MpResponseCategoryList(  //TBD
                responseId = FUID.id() ,
                onRequestId = requestId,
                endTime = Instant.now().toString(),
                status = ResponseStatusDto.FAIL,
                errors = listOf(
                    ErrorDto(
                        message = e.message,
                        level = ErrorDto.ErrorLevelDto.ERROR,
                        code = "MP-E-0018"
                    )
                )
            )
            pipelineContext.call.respond(res)
        }
    }
}

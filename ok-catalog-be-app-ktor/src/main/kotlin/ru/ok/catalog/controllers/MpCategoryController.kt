package ru.ok.catalog.controllers

import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.util.pipeline.*
import org.slf4j.LoggerFactory
import ru.ok.catalog.be.common.FUID
import ru.ok.catalog.be.common.models.CategoryType
import ru.ok.catalog.business.logic.backend.CategoryLogic
import ru.ok.catalog.transport.kmp.models.category.*
import ru.ok.catalog.transport.kmp.models.common.ResponseStatusDto
import ru.ok.catalog.transport.kmp.models.common.ErrorDto
import ru.ok.catalog.transport.kmp.models.common.MpMessage
import java.time.Instant
import ru.ok.catalog.backend.mappers.*
import ru.ok.catalog.be.common.context.IMpError
import ru.ok.catalog.be.common.context.MpBeContext
import ru.ok.catalog.be.mappers.MapperStatus
import ru.ok.catalog.be.mappers.init
import ru.ok.catalog.be.mappers.toDto

class MpCategoryController (
    private val blgc: CategoryLogic = CategoryLogic()

    ){
    private val log = LoggerFactory.getLogger(this::class.java)!!

    suspend fun create(pipelineContext: PipelineContext<Unit, ApplicationCall>): Unit = MpBeContext().run {
        try {
            //достать запрос в виде DTO
            val req = pipelineContext.call.receive<MpMessage>() as MpRequestCategoryCreate  //TBD
            //преобразовать в контекст
            init(req)
            //вызвать pipeline
            blgc.create(this)
            //преобразовать ответ в DTO
            //создать ответ
            val res: MpMessage = MpResponseCategoryCreate(  //TBD
                responseId = FUID.id(),
                onRequestId = requestId,
                endTime = Instant.now().toString(),
                status = MapperStatus.toDto(status),
                category = responseCategory.toDto()
            )
            //отправить ответ
            pipelineContext.call.respond(res)
        } catch (e:Throwable) {
            log.error("Read chain error", e)
            val res: MpMessage = MpResponseCategoryCreate(  //TBD
                responseId = FUID.id() ,
                onRequestId = requestId,
                endTime = Instant.now().toString(),
                status = ResponseStatusDto.FAIL,
                errors = listOf(
                    ErrorDto(
                        message = e.message,
                        level = ErrorDto.ErrorLevelDto.ERROR
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
            var ctx = MpBeContext()
            ctx.init(req)
            //вызвать обработчик
            blgc.read(ctx)
            //создать ответ, представить данные в DTO
            val res: MpMessage = MpResponseCategoryRead(    //TBD
                responseId = FUID.id(),
                onRequestId = req.requestId,
                endTime = Instant.now().toString(),
                //TODO mapper статуса в мапперы
                status = MapperStatus.toDto(ctx.status),
                errors = ctx.errors.map {
                    it.toDto()
                }.ifEmpty { null },
                category = ctx.responseCategory.toDto(),
            )
            //отправить ответ
            pipelineContext.call.respond(res)
        } catch (e:Throwable) {
            log.error("Read chain error", e)
            val res: MpMessage = MpResponseCategoryRead(    //TBD
                responseId = FUID.id(),
                onRequestId = requestId,
                endTime = Instant.now().toString(),
                status = ResponseStatusDto.FAIL,
                errors = listOf(
                    ErrorDto(
                        message = e.message,
                        level = ErrorDto.ErrorLevelDto.ERROR
                    )
                )
            )
            pipelineContext.call.respond(res)
        }
    }

    suspend fun update(pipelineContext: PipelineContext<Unit, ApplicationCall>) {
        var requestId: String? = null
        try {
            //достать запрос
            val req = pipelineContext.call.receive<MpMessage>() as MpRequestCategoryUpdate  //TBD
            requestId = req.requestId
            val data = req.updateData
            //создать ответ
            val res: MpMessage = MpResponseCategoryUpdate(  //TBD
                responseId = FUID.id(),
                onRequestId = requestId,
                endTime = Instant.now().toString(),
                status = ResponseStatusDto.SUCCESS,
                category = MpCategoryDto(
                    id = "cat-57",
                    type = data?.type,
                    title = data?.title,
                    code = data?.code,
                    upRefId = data?.upRefId,
                    isRoot = false,
                    isLeaf = true,
                )
            )
            //отправить ответ
            pipelineContext.call.respond(res)
        } catch (e:Throwable) {
            log.error("Read chain error", e)
            val res: MpMessage = MpResponseCategoryUpdate(  //TBD
                //responseId = "res-123",
                responseId = FUID.id() ,
                onRequestId = requestId,
                endTime = Instant.now().toString(),
                status = ResponseStatusDto.FAIL,
                errors = listOf(
                    ErrorDto(
                        message = e.message,
                        level = ErrorDto.ErrorLevelDto.ERROR
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
            val res: MpMessage = MpResponseCategoryDelete(    //TBD
                responseId = FUID.id(),
                onRequestId = req.requestId,
                endTime = Instant.now().toString(),
                status = ResponseStatusDto.SUCCESS,
                category = MpCategoryDto(
                    id = req.categoryId,
                    title = "Машиностроение",
                )
            )

            pipelineContext.call.respond(res)
        } catch (e:Throwable) {
            log.error("Read chain error", e)
            val res: MpMessage = MpResponseCategoryDelete(    //TBD
                responseId = FUID.id(),
                onRequestId = requestId,
                endTime = Instant.now().toString(),
                status = ResponseStatusDto.FAIL,
                errors = listOf(
                    ErrorDto(
                        message = e.message,
                        level = ErrorDto.ErrorLevelDto.ERROR
                    )
                )
            )
            pipelineContext.call.respond(res)
        }
    }

    suspend fun list(pipelineContext: PipelineContext<Unit, ApplicationCall>) {
        var requestId: String? = null
        try {
            //достать запрос
            val req = pipelineContext.call.receive<MpMessage>() as MpRequestCategoryList  //TBD
            requestId = req.requestId
            val data = req.filterData
            //создать ответ
            val res: MpMessage = MpResponseCategoryList(  //TBD
                responseId = FUID.id(),
                onRequestId = requestId,
                endTime = Instant.now().toString(),
                status = ResponseStatusDto.SUCCESS,
                categories = listOf(
                    MpCategoryDto(
                        id = "cat-57",
                        type = CategoryType.PRODUCTION.toString(),
                        code = "06.20.1",
                        title = "Добыча природного газа",
                        upRefId = "cat-50",
                        isRoot = false,
                        isLeaf = true,
                    )
                )
            )
            //отправить ответ
            pipelineContext.call.respond(res)
        } catch (e:Throwable) {
            log.error("Read chain error", e)
            val res: MpMessage = MpResponseCategoryList(  //TBD
                responseId = FUID.id() ,
                onRequestId = requestId,
                endTime = Instant.now().toString(),
                status = ResponseStatusDto.FAIL,
                errors = listOf(
                    ErrorDto(
                        message = e.message,
                        level = ErrorDto.ErrorLevelDto.ERROR
                    )
                )
            )
            pipelineContext.call.respond(res)
        }
    }
}

fun IMpError.toDto() =
    ErrorDto(
        code = code,
        message = message,
        field = field,
        level = when(level){
            IMpError.Level.INFO -> ErrorDto.ErrorLevelDto.INFO
            IMpError.Level.WARN -> ErrorDto.ErrorLevelDto.WARNING
            IMpError.Level.ERROR -> ErrorDto.ErrorLevelDto.ERROR
            IMpError.Level.FATAL -> ErrorDto.ErrorLevelDto.ERROR
        },
        group = group.toString()
    )


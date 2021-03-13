package ru.ok.catalog.controllers

import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.util.pipeline.*
import org.slf4j.LoggerFactory
import ru.ok.catalog.be.common.FUID
import ru.ok.catalog.be.common.models.CategoryType
import ru.ok.catalog.transport.kmp.models.category.*
import ru.ok.catalog.transport.kmp.models.common.ResponseStatusDto
import ru.ok.catalog.transport.kmp.models.common.ErrorDto
import ru.ok.catalog.transport.kmp.models.common.MpMessage
import java.time.Instant
import java.util.UUID.randomUUID

class MpCategoryController {
    private val log = LoggerFactory.getLogger(this::class.java)!!

    suspend fun create(pipelineContext: PipelineContext<Unit, ApplicationCall>) {
        var requestId: String? = null
        try {
            //достать запрос
            val req = pipelineContext.call.receive<MpMessage>() as MpRequestCategoryCreate  //TBD
            requestId = req.requestId
            val data = req.createData
            //создать ответ
            val res: MpMessage = MpResponseCategoryCreate(  //TBD
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
            val res: MpMessage = MpResponseCategoryCreate(  //TBD
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

    suspend fun read(pipelineContext: PipelineContext<Unit, ApplicationCall>) {
        var requestId: String? = null
        try {
            val req = pipelineContext.call.receive<MpMessage>() as MpRequestCategoryRead    //TBD
            requestId = req.requestId
            val res: MpMessage = MpResponseCategoryRead(    //TBD
                responseId = FUID.id(),
                onRequestId = req.requestId,
                endTime = Instant.now().toString(),
                status = ResponseStatusDto.SUCCESS,
                category = MpCategoryDto(
                    id = "cat-57",
                    title = "Машиностроение",
                )
            )

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

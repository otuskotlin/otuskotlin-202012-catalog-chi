package ru.ok.catalog.controllers

import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.util.pipeline.*
import org.slf4j.LoggerFactory
import ru.ok.catalog.be.common.FUID
import ru.ok.catalog.be.common.models.CategoryType
import ru.ok.catalog.transport.kmp.models.category.*
import ru.ok.catalog.transport.kmp.models.classification.*
import ru.ok.catalog.transport.kmp.models.common.ErrorDto
import ru.ok.catalog.transport.kmp.models.common.MpMessage
import ru.ok.catalog.transport.kmp.models.common.ResponseStatusDto
import java.time.Instant

class MpClassificationApiHttpAdapter {
    private val log = LoggerFactory.getLogger(this::class.java)!!

    suspend fun create(pipelineContext: PipelineContext<Unit, ApplicationCall>) {
        var requestId: String? = null
        try {
            //достать запрос
            val req = pipelineContext.call.receive<MpMessage>() as MpRequestClassificationCreate  //TBD
            requestId = req.requestId
            val data = req.createData
            //создать ответ
            val res: MpMessage = MpResponseClassificationCreate(  //TBD
                responseId = FUID.id(),
                onRequestId = requestId,
                endTime = Instant.now().toString(),
                status = ResponseStatusDto.SUCCESS,
                classification = MpClassificationDto(
                    id = "cls-22",
                    productId = "prd-17",
                    categoryId = "cat-50"
                )
            )
            //отправить ответ
            pipelineContext.call.respond(res)
        } catch (e:Throwable) {
            log.error("Read chain error", e)
            val res: MpMessage = MpResponseClassificationCreate(  //TBD
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
            val req = pipelineContext.call.receive<MpMessage>() as MpRequestClassificationDelete    //TBD
            requestId = req.requestId
            val res: MpMessage = MpResponseClassificationDelete(    //TBD
                responseId = FUID.id(),
                onRequestId = req.requestId,
                endTime = Instant.now().toString(),
                status = ResponseStatusDto.SUCCESS,
                classification = MpClassificationDto(
                    id = "cls-22",
                    productId = "prd-17",
                    categoryId = "cat-50"
                )
            )

            pipelineContext.call.respond(res)
        } catch (e:Throwable) {
            log.error("Read chain error", e)
            val res: MpMessage = MpResponseClassificationDelete(    //TBD
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
            val req = pipelineContext.call.receive<MpMessage>() as MpRequestClassificationList  //TBD
            requestId = req.requestId
            val data = req.filterData
            //создать ответ
            val res: MpMessage = MpResponseClassificationList(  //TBD
                responseId = FUID.id(),
                onRequestId = requestId,
                endTime = Instant.now().toString(),
                status = ResponseStatusDto.SUCCESS,
                classifications = listOf(
                    MpClassificationListItemDto(
                        id = "cls-22",
                        productId = "prd-17",
                        categoryId = "cat-57",
                        classificationPath = listOf(
                            MpCategoryDto(
                                id = "cat-50",
                                type = CategoryType.PRODUCTION.toString(),
                                code = "06.20",
                                title = "Добыча природного газа и газового конденсата",
                                isRoot = true,
                                isLeaf = false,
                            ),
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
                )
            )
            //отправить ответ
            pipelineContext.call.respond(res)
        } catch (e:Throwable) {
            log.error("Read chain error", e)
            val res: MpMessage = MpResponseClassificationList(  //TBD
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

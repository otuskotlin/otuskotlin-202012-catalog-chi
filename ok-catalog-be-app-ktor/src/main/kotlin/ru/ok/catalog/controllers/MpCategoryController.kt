package ru.ok.catalog.controllers

import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.util.pipeline.*
import org.slf4j.LoggerFactory
import ru.ok.catalog.transport.kmp.models.category.MpCategoryDto
import ru.ok.catalog.transport.kmp.models.category.MpRequestCategoryRead
import ru.ok.catalog.transport.kmp.models.category.MpResponseCategoryRead
import ru.ok.catalog.transport.kmp.models.common.EResponseStatusDto
import ru.ok.catalog.transport.kmp.models.common.ErrorDto
import ru.ok.catalog.transport.kmp.models.common.MpMessage
import java.time.Instant

class MpCategoryController {
    private val log = LoggerFactory.getLogger(this::class.java)!!

    suspend fun read(pipelineContext: PipelineContext<Unit, ApplicationCall>) {
        var requestId: String? = null
        try {
            val req = pipelineContext.call.receive<MpMessage>() as MpRequestCategoryRead
            requestId = req.requestId
            val res: MpMessage = MpResponseCategoryRead(
                responseId = "res-123",
                onRequestId = req.requestId,
                endTime = Instant.now().toString(),
                status = EResponseStatusDto.SUCCESS,
                category = MpCategoryDto(
                    id = "cat-57",
                    title = "Машиностроение",
                )
            )

            pipelineContext.call.respond(res)
        } catch (e:Throwable) {
            log.error("Read chain error", e)
            val res: MpMessage = MpResponseCategoryRead(
                responseId = "res-123",
                onRequestId = requestId,
                endTime = Instant.now().toString(),
                status = EResponseStatusDto.FAIL,
                //TODO: отдавать информацию об исключении
                //TODO: логгировать исключение
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

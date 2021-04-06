package ru.ok.catalog.be.mappers

import ru.ok.catalog.be.common.context.IMpError
import ru.ok.catalog.transport.kmp.models.common.ErrorDto

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
            IMpError.Level.OFF -> ErrorDto.ErrorLevelDto.INFO
        },
        group = group.toString()
    )

package ru.ok.catalog.backend.mappers

import ru.ok.catalog.be.common.context.MpBeContextStatus
import ru.ok.catalog.transport.kmp.models.common.ResponseStatusDto

object MapperStatus {
    fun toDto(status: MpBeContextStatus): ResponseStatusDto {
        return when(status) {
            MpBeContextStatus.SUCCESS -> ResponseStatusDto.SUCCESS
            MpBeContextStatus.ERROR -> ResponseStatusDto.ERROR
            else -> ResponseStatusDto.FAIL
        }
    }
}
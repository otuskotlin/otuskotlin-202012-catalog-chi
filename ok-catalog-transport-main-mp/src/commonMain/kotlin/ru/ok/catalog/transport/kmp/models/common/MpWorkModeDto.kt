package ru.ok.catalog.transport.kmp.models.common

import kotlinx.serialization.Serializable

@Serializable
enum class MpWorkModeDto {
    PROD,
    STUB
}

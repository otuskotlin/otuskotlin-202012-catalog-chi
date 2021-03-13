package ru.ok.catalog.transport.kmp.models.common

import kotlinx.serialization.Serializable

@Serializable
data class ErrorDto (
    val code: String? = null,
    val message: String? = null,
    val field: String? = null,
    val level: ErrorLevelDto? = null,
    val group: String? = null

) {

    @Serializable
    enum class ErrorLevelDto {
        SUCCESS,
        INFO,
        WARNING,
        ERROR
    }
}

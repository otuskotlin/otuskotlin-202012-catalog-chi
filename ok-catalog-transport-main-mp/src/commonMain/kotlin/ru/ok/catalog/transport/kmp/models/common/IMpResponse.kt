package ru.ok.catalog.transport.kmp.models.common

interface IMpResponse {
    val responseId: String?
    val onRequestId: String?
    val endTime: String?
    val errors: List<ErrorDto>?
    val status: EResponseStatusDto?
    val debug: IMpDebug?
}

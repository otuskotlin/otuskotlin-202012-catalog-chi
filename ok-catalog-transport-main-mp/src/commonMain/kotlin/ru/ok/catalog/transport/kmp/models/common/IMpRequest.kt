package ru.ok.catalog.transport.kmp.models.common

interface IMpRequest {
    val requestId: String?
    val onResponseId: String?
    val timeStart: String?
    val debug: IMpDebug?
}

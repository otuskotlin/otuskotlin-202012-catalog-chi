package ru.ok.catalog.transport.kmp.models.common

interface IMpDebug {
    val mode: MpWorkModeDto?
    val stubCase: String?
    val stubParams: Map<String,String>?
}

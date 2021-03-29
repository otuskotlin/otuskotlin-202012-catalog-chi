package ru.ok.catalog.be.common.context

enum class MpBeContextStatus {
    NONE,
    RUNNING,
    FINISHING,
    SUCCESS,
    FAILING,
    ERROR;

    val isError:Boolean
            get() = this in setOf(FAILING, ERROR)

}
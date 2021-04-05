package ru.ok.catalog.be.common.context

import java.util.logging.Level
import javax.swing.GroupLayout

interface IMpError {
    val code: String
    val group: Group
    val field: String
    val level: Level
    val message: String

    enum class Group(
        val alias: String
    ) {
        NONE(""),
        VALIDATION("validation"),
        EXCEPTION("internal server")
    }

    enum class Level(val weight: Int) {
        FATAL(90),
        ERROR(70),
        WARN(40),
        INFO(20);

        val isError: Boolean
            get() = weight >= ERROR.weight
    }



}

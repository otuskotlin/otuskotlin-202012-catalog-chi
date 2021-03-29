package ru.ok.catalog.business.logic.backend.pipelines

import ru.ok.catalog.be.common.context.IMpError

data class MpError(
    override val code: String = "",
    override val group: IMpError.Group = IMpError.Group.NONE,
    override val field: String = "",
    override val level: IMpError.Level = IMpError.Level.INFO,
    override val message: String = ""
): IMpError {

}

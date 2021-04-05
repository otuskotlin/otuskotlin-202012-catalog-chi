package ru.ok.catalog.business.logic.backend.validators

import ru.ok.catalog.be.common.context.IMpError

object ErrorLevelService {
    //в реальной жизни в нашей области требуется управлять
    //уровнем ошибок через хранимые в БД настройки
    //в начальной реализации соотвествие уровням задается в коде здесь
    var levels = mapOf<String, IMpError.Level>(
        "MP-E-0034" to IMpError.Level.OFF,
        "MP-E-0035" to IMpError.Level.INFO
    )

    fun level(errorCode: String) =
        levels.getOrDefault(errorCode,IMpError.Level.ERROR)

}
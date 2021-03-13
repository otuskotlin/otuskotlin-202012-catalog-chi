package ok.catalog.tools

import java.text.SimpleDateFormat
import java.util.*

fun main() {
    genForClass("Category","title","CRUDL")
    //genForClass("Classification","productId","CDL")
}

fun genForClass(cls: String, commonField: String, ops:String = "CRUDL") {
    //эта фукция генерирует заготовку для полного теста транспортных моделей
    //для превращения в тест заготовка может требовать небольшой ручной правки
    val lcCls = cls.toLowerCase()
    if (ops.contains("C")) {
        println(genTest(cls, "Create", commonField, "createData", false, "${lcCls}Id"))
    }
    if (ops.contains("R")) {
        println(genTest(cls,"Read",commonField,"${lcCls}Id",true, "${lcCls}Id"))
    }
    if (ops.contains("U")) {
        println(genTest(cls,"Update",commonField,"updateData",false, "${lcCls}Id"))
    }
    if (ops.contains("D")) {
        println(genTest(cls,"Delete",commonField,"${lcCls}Id",true, "${lcCls}Id"))
    }
    if (ops.contains("L")) {
        println(genTest(cls,"List","type","filterData",false, "${lcCls}Id"))
    }

}

fun genTest(cls: String, op: String, commonReqField: String, arg: String, simpleArg: Boolean, commonResField: String):String {
    val timeStamp: String = SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(Date())
    val lcCls = cls.toLowerCase()
    val payload = if ( op != "List" ) {
        if ( simpleArg ) {
            """
        $arg = "Test for $op request",
        """.trimMargin()
        } else {
            """
            $arg = Mp$cls${op}Dto(
                $commonReqField = "Test for $op request",
            )
        """.trimMargin()
        }
    } else {
        if ( simpleArg ) {
            """
        $arg = "Test for $op request",
        """.trimMargin()
        } else {
            """
            $arg = Mp$cls${op}FilterDto(
                $commonReqField = "Test for $op request",
            )
        """.trimMargin()
        }
    }
    return if ( op != "List" ) {
        """
        //
        // проверка $op $cls
        // generated $timeStamp
        //
        val req$op = MpRequest$cls$op(
            $payload
        )
        val req${op}Str = json.encodeToString(MpMessage.serializer(), req$op)
        assertTrue{ req${op}Str.contains("Test for $op request")}
        val dtoReq$op = json.decodeFromString(MpMessage.serializer(), req${op}Str)
        assertEquals(req$op, (dtoReq$op as? MpRequest$cls$op))

        val res${op} = MpResponse${cls}${op}(
            $lcCls = Mp${cls}Dto(
                id = "Test for ${op} response",
            )
        )
        val res${op}Str = json.encodeToString(MpMessage.serializer(), res${op})
        assertTrue{ res${op}Str.contains("Test for ${op} response")}
        val dtoRes${op} = json.decodeFromString(MpMessage.serializer(), res${op}Str)
        assertEquals(res${op}, (dtoRes${op} as? MpResponse${cls}${op}))
        """.trimMargin()
    } else {
        """
        //
        // проверка $op $cls
        // generated $timeStamp
        //
        val req$op = MpRequest$cls$op(
            $payload
        )
        val req${op}Str = json.encodeToString(MpMessage.serializer(), req$op)
        assertTrue{ req${op}Str.contains("Test for $op request")}
        val dtoReq$op = json.decodeFromString(MpMessage.serializer(), req${op}Str)
        assertEquals(req$op, (dtoReq$op as? MpRequest$cls$op))

        val res${op} = MpResponse${cls}${op}(
            ${lcCls}s = listOf(Mp${cls}Dto(
                id = "Test for ${op} response",
            ))
        )
        val res${op}Str = json.encodeToString(MpMessage.serializer(), res${op})
        assertTrue{ res${op}Str.contains("Test for ${op} response")}
        val dtoRes${op} = json.decodeFromString(MpMessage.serializer(), res${op}Str)
        assertEquals(res${op}, (dtoRes${op} as? MpResponse${cls}${op}))
        """.trimMargin()
    }
}
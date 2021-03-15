import org.junit.jupiter.api.Test
import ru.ok.catalog.be.common.context.MpBeContext
import ru.ok.catalog.be.common.models.EMpCategoryType
import ru.ok.catalog.be.common.models.MpCategoryIdModel
import ru.ok.catalog.be.common.models.MpCategoryModel
import ru.ok.catalog.transport.kmp.models.category.*
import ru.ok.catalog.transport.kmp.models.common.IMpRequest
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class MappersTest {
    @Test
    fun requestMappersTest() {
        val request = MpRequestCategoryCreate(
            createData = MpCategoryCreateDto(
                title = "Машиностроение",
                type = "PRODUCTION",
                code = "28.41"
            )
        )

        val context = MpBeContext()

        context.setQuery(request)
        assertEquals("Машиностроение", context.requestCategory.title)
        assertEquals(EMpCategoryType.PRODUCTION,context.requestCategory.type)
        assertEquals(null,context.requestCategory.isLeaf)
        assertEquals("28.41",context.requestCategory.code)
    }

    @Test
    fun dtoMappersTest() {
        val category = MpCategoryModel(
            id = MpCategoryIdModel("cat-57"),
            type = EMpCategoryType.PRODUCTION,
            title = "Машиностроение",
            code = "28.41",
            upRefId = MpCategoryIdModel.NONE,
            isLeaf = false
        )

        val dto = category.toDto()

        assertEquals("Машиностроение", dto.title)
        assertEquals("PRODUCTION",dto.type)
        assertEquals("28.41",dto.code)
        assertEquals(null,dto.upRefId)
        assertEquals(false, dto.isLeaf)
        assertEquals(true, dto.isRoot)
        assertEquals("cat-57", dto.id)
    }

    @Test
    fun kotlinTest() {
        var s1: String? = null
        var s2: String?
        var s3: String = "xxx"
        var s4: String = ""

        assertTrue(s1 == null)
        assertFalse(s1 == "")
        s2 = s1.toString()
        assertFalse(s2 == "")
        s2 = s3.toString()
        assertEquals(s3,s2)
//        s2 = s1.nvl("NULL")
//        assertEquals("NULL",s2)
//        s2 = s3.nvl("NULL")
//        assertEquals("xxx",s2)
        s2 = s1?:"NULL"
        assertEquals("NULL",s2)
        s2 = s3?:"NULL"
        assertEquals("xxx",s2)
        s2 = s4.takeIf{ it.isNotBlank()}
        assertEquals(null,s2)
        s2 = s3.takeIf{ it.isNotBlank()}
        assertEquals("xxx",s2)
    }
}

//fun <T> T?.nvl(default: T): T {
//    return this ?: default
//}

fun  String.bnl(): String? {
    return if ( this == "") { null } else { this }
}

private fun MpBeContext.setQuery(request: IMpRequest) =
    when(request){
        is MpRequestCategoryCreate -> this.setQuery(request)
        is MpRequestCategoryRead -> this.setQuery(request)
        is MpRequestCategoryUpdate -> this.setQuery(request)
        is MpRequestCategoryDelete -> this.setQuery(request)
        else -> null
}

private fun MpBeContext.setQuery(request: MpRequestCategoryCreate){
    request.createData?.let { data ->
        this.requestCategory = MpCategoryModel(
            title = data.title?:"",
            code = data.code?:"",
            //TODO: где делать валидацию type и возвращать ошибку?
            type = EMpCategoryType.valueOf(data.type?:"MARKETPLACE"),
            upRefId = MpCategoryIdModel(data.upRefId?:""),
        )
    }
}

private fun MpBeContext.setQuery(request: MpRequestCategoryRead) {
    //this.requestCategoryId = request.categoryId?.let { MpCategoryIdModel(it) }?: MpCategoryIdModel.NONE
    this.requestCategoryId = if ( request.categoryId == null ) {
        MpCategoryIdModel.NONE
    } else {
        MpCategoryIdModel(request.categoryId!!)
    }
}

private fun MpBeContext.setQuery(request: MpRequestCategoryUpdate) {
    request.updateData?.let { data ->
        this.requestCategory = MpCategoryModel(
            title = data.title ?: "",
            code = data.code ?: "",
            //TODO: где делать валидацию type и возвращать ошибку?
            type = EMpCategoryType.valueOf(data.type ?: "MARKETPLACE"),
            upRefId = MpCategoryIdModel(data.upRefId ?: ""),
            //TODO: валидация на непустое значение
            id = MpCategoryIdModel(data.id ?: ""),
        )
    }
}

private fun MpBeContext.setQuery(request: MpRequestCategoryDelete) {
    this.requestCategoryId = if ( request.categoryId == null ) {
        MpCategoryIdModel.NONE
    } else {
        MpCategoryIdModel(request.categoryId!!)
    }
}

private fun MpCategoryModel.toDto() =
    MpCategoryDto(
        //someObject?.takeIf{ status }?.apply{ doThis() }
        //someObject?.takeIf{ status }?.doThis()
        //id = this.id.id.takeIf{it.isNotBlank()},    //для преобразования пустых строк в null
        id = this.id.id.bnl(),
        title = this.title,
        code = this.code,
        type = this.type.name,
        upRefId = this.upRefId.id.bnl(),
        isLeaf = this.isLeaf,
        isRoot = this.upRefId.id == ""
    )

//так делается обработка исключений
//try {
//    myVar = MyEnum.valueOf("Qux")
//} catch(e: IllegalArgumentException) {
//    Log.d(TAG, "INVALID MyEnum value: 'Qux' | $e")
//}
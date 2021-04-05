import org.junit.jupiter.api.Test
import ru.ok.catalog.be.common.context.MpBeContext
import ru.ok.catalog.be.common.models.CategoryType
import ru.ok.catalog.be.common.models.MpCategoryIdModel
import ru.ok.catalog.be.common.models.MpCategoryModel
import ru.ok.catalog.transport.kmp.models.category.*
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import ru.ok.catalog.be.mappers.init
import ru.ok.catalog.be.mappers.toDto

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

        context.init(request)
        assertEquals("Машиностроение", context.qryCategory.title)
        assertEquals(CategoryType.PRODUCTION,context.qryCategory.type)
        assertEquals(null,context.qryCategory.isLeaf)
        assertEquals("28.41",context.qryCategory.code)
    }

    @Test
    fun dtoMappersTest() {
        val category = MpCategoryModel(
            id = MpCategoryIdModel("cat-57"),
            type = CategoryType.PRODUCTION,
            title = "Машиностроение",
            code = "28.41",
            upRefId = MpCategoryIdModel.NONE,
            isLeaf = false
        )

        val dto = category.toDto()

        assertEquals("Машиностроение", dto?.title)
        assertEquals("PRODUCTION",dto?.type)
        assertEquals("28.41",dto?.code)
        assertEquals(null,dto?.upRefId)
        assertEquals(false, dto?.isLeaf)
        assertEquals(true, dto?.isRoot)
        assertEquals("cat-57", dto?.id)
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

//так делается обработка исключений
//try {
//    myVar = MyEnum.valueOf("Qux")
//} catch(e: IllegalArgumentException) {
//    Log.d(TAG, "INVALID MyEnum value: 'Qux' | $e")
//}
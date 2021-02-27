import ru.ok.catalog.common.mp.someFun
import kotlin.test.Test
import kotlin.test.assertTrue

class SomeFunKtTestLinux {

    @Test
    fun someFunTest() {
        val tmp: String
        tmp = someFun("Hello")
        assertTrue(tmp.contains("Linux:"))
    }
}
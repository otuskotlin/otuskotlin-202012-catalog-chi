import org.junit.Test
import ru.ok.catalog.common.mp.someFun
import kotlin.test.assertTrue

internal class SomeFunTestJvm {
    @Test

    fun someFunTestJvm () {
        var str = "SomeFun"
        assertTrue {
            someFun(str).contains(str)
            someFun(str).contains("Jvm")
        }
    }
}
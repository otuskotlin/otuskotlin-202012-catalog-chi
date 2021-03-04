package ru.ok.catalog.common.mp
import kotlin.test.Test
import kotlin.test.assertTrue

class SomeFunKtTestJs {
    @Test
    fun someFunTest() {
        val tmp: String
        tmp = someFun("Hello")
        assertTrue(tmp.contains("JS:"))
    }
}
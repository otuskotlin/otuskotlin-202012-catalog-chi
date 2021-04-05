package ru.ok.catalog.common.mp.validation

import ru.ok.catalog.common.mp.validation.homework.calcAccountKeyRU
import ru.ok.catalog.common.mp.validation.homework.getPartyCode
import ru.ok.catalog.common.mp.validation.homework.translate
import kotlin.test.Test
import kotlin.test.assertEquals

class CalcAccountKeyRUTest {
    @Test

    fun calcAccountKeyRUTest() {
        //044030653, 40702810455000000123
        assertEquals("4", calcAccountKeyRU("653","40702810455000000123"))
        assertEquals("6", calcAccountKeyRU("653","40702A76655000000123"))
    }

    @Test
    fun translateTest() {
        assertEquals("14356", translate("1A3BC","ABCX","456Z"))
        assertEquals("1A3BC", translate("1A3BC","","456Z"))
        assertEquals("1435", translate("1AC3BC","ABCX","45"))
    }

    @Test
    fun getPartyCodeTest() {
        assertEquals("653", getPartyCode("044030653"))
        assertEquals("030", getPartyCode("044030001"))
    }
}
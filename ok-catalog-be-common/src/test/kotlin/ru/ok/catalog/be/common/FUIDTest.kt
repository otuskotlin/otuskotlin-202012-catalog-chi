package ru.ok.catalog.be.common

import kotlin.test.Test
import kotlin.test.assertEquals

class FUIDTest {
    @Test

    fun testFUID() {
        assertEquals("00000000000" , FUID.lb64(0) )
        assertEquals("00000000009" , FUID.lb64(9) )
        assertEquals("0000000000A" , FUID.lb64(10) )
        assertEquals("0000000000$" , FUID.lb64(63) )
        assertEquals("7$$$$$$$$$$" , FUID.lb64(Long.MAX_VALUE) )
        println(FUID.id())
    }

}
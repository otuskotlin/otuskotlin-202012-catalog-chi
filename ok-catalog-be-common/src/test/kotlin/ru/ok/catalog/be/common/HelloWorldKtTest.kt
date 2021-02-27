package ru.ok.catalog.be.common

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class HelloWorldKtTest {
    @Test

    //Проверка некоторых аспектов классического K&R сообщения Hello, world!
    fun testHelloMessage() {
        var tmp: String
        tmp = helloMessage("Hello", "world")

        //содержит ","
        assertTrue(tmp.contains(","))

        //первая буква прописная
        assertEquals(tmp.substring(0 .. 0).toUpperCase() , tmp.substring(0 .. 0) )

        //в конце - "!"
        assertEquals( "!", tmp.substring(tmp.length - 1))

        tmp = helloMessage("Привет", "Илья")
        //первая буква прописная
        assertEquals(tmp.substring(0 .. 0).toUpperCase() , tmp.substring(0 .. 0) )
    }

}
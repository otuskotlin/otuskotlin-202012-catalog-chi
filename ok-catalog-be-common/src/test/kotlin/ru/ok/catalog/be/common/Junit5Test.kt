package ru.ok.catalog.be.common

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class Junit5Test {
    @Test

    fun junit5Test() {

    }

    @Nested
    inner class NestedDemo {
        @Test
        fun `all fields are included`() {}
        @Test
        fun `limit parameter`() {}
        @Test
        fun `по русски`() {}
    }

    @ParameterizedTest
    @ValueSource(strings = [
        "one",
        "two",
        "three"
    ])

    fun paramTest(str: String) {
        println(str)
    }
}
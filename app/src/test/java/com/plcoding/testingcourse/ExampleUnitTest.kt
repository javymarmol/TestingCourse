package com.plcoding.testingcourse

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @BeforeEach
    fun setup() {

    }

    @AfterEach
    fun tearDown() {

    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
        val sum = 2 + 3
        assertThat(sum).isEqualTo(4)
    }
}
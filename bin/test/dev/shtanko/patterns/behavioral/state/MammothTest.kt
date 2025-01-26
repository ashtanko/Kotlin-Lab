/*
 * Copyright 2020 Oleksii Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.patterns.behavioral.state

import dev.shtanko.patterns.utils.InMemoryAppender
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MammothTest {

    private lateinit var appender: InMemoryAppender

    @BeforeEach
    fun setUp() {
        appender = InMemoryAppender()
    }

    @AfterEach
    fun tearDown() {
        appender.stop()
    }

    @Test
    fun `time passes test`() {
        val mammoth = Mammoth()
        mammoth.observe()
        assertEquals("The mammoth is calm and peaceful.", appender.lastMessage)
        assertEquals(1, appender.logSize)

        mammoth.timePasses()
        assertEquals("The mammoth gets angry!", appender.lastMessage)
        assertEquals(2, appender.logSize)

        mammoth.observe()
        assertEquals("The mammoth is furious!", appender.lastMessage)
        assertEquals(3, appender.logSize)

        mammoth.timePasses()
        assertEquals("The mammoth calms down.", appender.lastMessage)
        assertEquals(4, appender.logSize)

        mammoth.observe()
        assertEquals("The mammoth is calm and peaceful.", appender.lastMessage)
        assertEquals(5, appender.logSize)
    }

    @Test
    fun `to string test`() {
        val str = Mammoth().toString()
        assertNotNull(str)
        assertEquals("The mammoth", str)
    }
}

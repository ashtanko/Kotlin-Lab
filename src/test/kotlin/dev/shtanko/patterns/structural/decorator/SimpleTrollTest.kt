/*
 * Copyright 2020 Alexey Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.patterns.structural.decorator

import dev.shtanko.patterns.utils.InMemoryAppender
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class SimpleTrollTest {

    private lateinit var appender: InMemoryAppender

    @BeforeEach
    fun setUp() {
        appender = InMemoryAppender(SimpleTroll::class.java)
    }

    @AfterEach
    fun tearDown() {
        appender.stop()
    }

    @Test
    internal fun `troll actions test`() {
        val troll = SimpleTroll()
        assertEquals(10, troll.getAttackPower())
        troll.attack()
        assertEquals("The troll tries to grab you!", appender.lastMessage)

        troll.fleeBattle()
        assertEquals("The troll shrieks in horror and runs away!", appender.lastMessage)
        assertEquals(2, appender.logSize)
    }
}

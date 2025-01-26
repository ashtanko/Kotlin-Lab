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

package dev.shtanko.patterns.structural.proxy

import dev.shtanko.kotlinlang.inline.each
import dev.shtanko.patterns.utils.InMemoryAppender
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class IvoryTowerTest {
    private lateinit var appender: InMemoryAppender

    @BeforeEach
    fun setUp() {
        appender = InMemoryAppender(IvoryTower::class.java)
    }

    @AfterEach
    fun tearDown() {
        appender.stop()
    }

    @Test
    internal fun `enter wizard test`() {
        val wizards = listOf(
            Wizard("Gandalf"),
            Wizard("Dumbledore"),
            Wizard("Oz"),
            Wizard("Merlin"),
        )
        val tower = IvoryTower()
        wizards.each(tower::enter)
        assertTrue(appender.logContains("Gandalf enters the tower."))
        assertTrue(appender.logContains("Dumbledore enters the tower."))
        assertTrue(appender.logContains("Oz enters the tower."))
        assertTrue(appender.logContains("Merlin enters the tower."))
        assertEquals(4, appender.logSize)
    }
}

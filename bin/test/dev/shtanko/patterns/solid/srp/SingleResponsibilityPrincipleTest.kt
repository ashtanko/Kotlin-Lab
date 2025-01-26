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

package dev.shtanko.patterns.solid.srp

import dev.shtanko.patterns.utils.InMemoryAppender
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SingleResponsibilityPrincipleTest {

    val robot = SingleResponsibilityPrinciple.Robot("Wally", "Cleaner")

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
    fun `when violation test`() {
        robot.greet()
        assertEquals("Hello my name is Wally, and I am a Cleaner robot", appender.lastMessage)
    }

    @Test
    fun `when solution test`() {
        val robotPrinter = SingleResponsibilityPrinciple.RobotPrinter()
        robotPrinter.greet(robot)
        assertEquals("Hello my name is Wally, and I am a Cleaner robot", appender.lastMessage)
    }
}

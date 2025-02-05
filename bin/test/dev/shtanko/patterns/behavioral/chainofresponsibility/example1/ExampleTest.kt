/*
 * Copyright 2022 Oleksii Shtanko
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

package dev.shtanko.patterns.behavioral.chainofresponsibility.example1

import dev.shtanko.patterns.utils.InMemoryAppender
import kotlin.test.assertTrue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ExampleTest {

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
    fun `chain of responsibility test`() {
        val king = OrcKing()
        king.makeRequest(Request(RequestType.DEFEND_CASTLE, "defend castle"))
        assertThat(appender.logSize).isEqualTo(1)
        assertTrue(appender.logContains("Orc commander handling request \"defend castle\""))
        king.makeRequest(Request(RequestType.TORTURE_PRISONER, "torture prisoner"))
        assertThat(appender.logSize).isEqualTo(2)
        assertTrue(appender.logContains("Orc officer handling request \"torture prisoner\""))
        king.makeRequest(Request(RequestType.COLLECT_TAX, "collect tax"))
        assertThat(appender.logSize).isEqualTo(3)
        assertTrue(appender.logContains("Orc soldier handling request \"collect tax\""))
    }
}

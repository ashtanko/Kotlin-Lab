/*
 * Copyright 2025 Oleksii Shtanko
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

package dev.shtanko.concurrency.coroutines.flow

import app.cash.turbine.Event
import app.cash.turbine.test
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ColdFlowsTest {

    @Test
    fun `cold flow - take 2 - AssertJ`() = runTest {
        val actual = mutableListOf<String>()
        flow {
            emit("one")
            emit("two")
            emit("three")
        }.take(2).collect {
            actual.add(it)
        }
        assertThat(actual).containsExactly("one", "two")
    }

    @Test
    fun `cold flow - take 2 - Turbine`() = runTest {
        flow {
            emit("one")
            emit("two")
            emit("three")
        }.take(2).test {
            assertThat(awaitItem()).isEqualTo("one")
            assertThat(awaitItem()).isEqualTo("two")
            awaitComplete()
        }
    }

    @Test
    fun `cold flow - take multiple - alternative - Turbine`() = runTest {
        flow {
            emit("one")
            emit("two")
            emit("three")
        }.take(2).test {
            val actual = cancelAndConsumeRemainingEvents()
            assertThat(actual).containsExactly(
                Event.Item("one"),
                Event.Item("two"),
                Event.Complete,
            )
        }
    }
}

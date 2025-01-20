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

package dev.shtanko.turbine

import app.cash.turbine.test
import kotlin.time.DurationUnit
import kotlin.time.toDuration
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class AdvancedUsageTest {

    @Test
    fun `advanced usage test`() = runTest {
        val timeFlow = flow {
            kotlinx.coroutines.delay(100)
            emit("Hello")
        }

        timeFlow.test {
            assertEquals("Hello", awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `mixed emissions test`() = runTest {
        flow {
            emit("A")
            emit("B")
            emit("C")
        }.test {
            assertEquals("A", awaitItem())
            assertEquals("B", awaitItem())
            assertEquals("C", awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `delayedFlow test`() = runTest {
        val delayedFlow = flow {
            delay(100)
            emit(1)
            delay(100)
            emit(2)
        }

        delayedFlow.test {
            assertEquals(1, awaitItem())
            assertEquals(2, awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `infinite flow with cancellation test`() = runTest(timeout = 2.toDuration(DurationUnit.SECONDS)) {
        val infiniteFlow = flow {
            var i = 0
            while (true) {
                delay(100L)
                emit(i++)
            }
        }

        infiniteFlow.test {
            assertEquals(0, awaitItem())
            assertEquals(1, awaitItem())
            assertEquals(2, awaitItem())
            cancelAndIgnoreRemainingEvents() // Stops collecting
        }
    }

    @Test
    fun `emissions with side effects test`() = runTest {
        val sideEffectFlow = flow {
            emit(1)
            emit(2)
            println("Side effect emitted!")
            emit(3)
        }

        sideEffectFlow.test {
            assertEquals(1, awaitItem())
            assertEquals(2, awaitItem())
            assertEquals(3, awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `emissions with error at the end testing`() = runTest {
        val errorFlow = flow {
            emit(1)
            emit(2)
            throw IllegalArgumentException("Flow error")
        }

        errorFlow.test {
            assertEquals(1, awaitItem())
            assertEquals(2, awaitItem())
            val error = awaitError()
            assertTrue(error is IllegalArgumentException)
            assertEquals("Flow error", error.message)
        }
    }

    @Test
    fun `flows with no emissions test`() = runTest {
        emptyFlow<Int>().test {
            awaitComplete()
        }
    }

    @Test
    fun `multiple subscribers test`() = runTest {
        val multiSubscriberFlow = flowOf(1, 2, 3)

        multiSubscriberFlow.test {
            assertEquals(1, awaitItem())
            assertEquals(2, awaitItem())
            assertEquals(3, awaitItem())
            awaitComplete()
        }

        multiSubscriberFlow.test {
            assertEquals(1, awaitItem())
            assertEquals(2, awaitItem())
            assertEquals(3, awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `expecting no events test`() = runTest {
        val quickCompleteFlow = flow<Int> { }

        quickCompleteFlow.test {
            awaitComplete()
        }
    }

    @Test
    fun `backpressure with limited buffer test`() = runTest {
        val backpressureFlow = flow {
            repeat(3) {
                delay(50)
                emit(it)
            }
        }

        backpressureFlow.test {
            assertEquals(0, awaitItem())
            delay(100) // Simulate delay before requesting the next item
            assertEquals(1, awaitItem())
            assertEquals(2, awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `complex transformations test`() = runTest {
        val transformedFlow = flow {
            emit(1)
            emit(2)
            emit(3)
        }.map { it * 2 }

        transformedFlow.test {
            assertEquals(2, awaitItem())
            assertEquals(4, awaitItem())
            assertEquals(6, awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `StateFlow test`() = runTest {
        val stateFlow = MutableStateFlow(0)
        stateFlow.value = 1
        stateFlow.value = 2

        stateFlow.test {
            assertEquals(2, awaitItem())
            expectNoEvents()
        }
    }
}

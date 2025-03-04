/*
 * Designed and developed by 2025 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.turbine

import app.cash.turbine.test
import app.cash.turbine.turbineScope
import kotlin.time.DurationUnit
import kotlin.time.toDuration
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class AdvancedUsageTest {

    @Test
    fun `advanced usage test`() = runTest {
        val timeFlow = flow {
            delay(100)
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

    @Test
    fun `channelFlow test`() = runTest {
        channelFlow {
            withContext(IO) {
                Thread.sleep(100)
                send("item")
            }
        }.test {
            assertEquals("item", awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `channelFlow 2 test`() = runTest {
        channelFlow {
            withContext(IO) {
                repeat(10) {
                    Thread.sleep(200)
                    send("item $it")
                }
            }
        }.test {
            assertEquals("item 0", awaitItem())
            assertEquals("item 1", awaitItem())
            assertEquals("item 2", awaitItem())
        }
    }

    @Test
    fun `flows can also be explicitly canceled at any point test`() = runTest {
        channelFlow {
            withContext(IO) {
                repeat(10) {
                    Thread.sleep(200)
                    send("item $it")
                }
            }
        }.test {
            Thread.sleep(700)
            cancel()

            assertEquals("item 0", awaitItem())
            assertEquals("item 1", awaitItem())
            assertEquals("item 2", awaitItem())
        }
    }

    @Test
    fun `names test`() = runTest {
        turbineScope {
            val turbine1 = flowOf(1).testIn(backgroundScope, name = "turbine 1")
            val turbine2 = flowOf(2).testIn(backgroundScope, name = "turbine 2")
            turbine1.awaitItem()
            turbine2.awaitItem()
            turbine1.awaitComplete() // comment
            turbine2.awaitComplete() // comment
        }
    }

    @Test
    fun `multiple flows test`() = runTest {
        turbineScope {
            val turbine1 = flowOf(1).testIn(backgroundScope)
            val turbine2 = flowOf(2).testIn(backgroundScope)
            assertEquals(1, turbine1.awaitItem())
            assertEquals(2, turbine2.awaitItem())
            turbine1.awaitComplete()
            turbine2.awaitComplete()
        }
    }

    @Test
    fun `testIn test`() = runTest {
        turbineScope {
            val turbine = flowOf("one", "two").testIn(backgroundScope)
            assertEquals("one", turbine.awaitItem())
            assertEquals("two", turbine.awaitItem())
            turbine.awaitComplete()
        }
    }

    @Test
    fun `cancelAndIgnoreRemainingEvents test`() = runTest {
        flowOf("one", "two").test {
            assertEquals("one", awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `most recent emitted item and ignore the previous ones test`() = runTest {
        flowOf("one", "two", "three")
            .map {
                delay(100)
                it
            }
            .test {
                // 0 - 100ms -> no emission yet
                // 100ms - 200ms -> "one" is emitted
                // 200ms - 300ms -> "two" is emitted
                // 300ms - 400ms -> "three" is emitted
                delay(250)
                assertEquals("two", expectMostRecentItem())
                cancelAndIgnoreRemainingEvents()
            }
    }

    @Test
    fun `flow termination test`() = runTest {
        flow { emit(throw RuntimeException("broken!")) }.test {
            assertEquals("broken!", awaitError().message)
        }
    }

    @Test
    fun `flow termination 2 test`() = runTest {
        // flow<Nothing> { throw RuntimeException("broken!") }.test { } // todo uncomment
    }
}

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

package dev.shtanko.concurrency.coroutines

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class CountdownTimerTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `countdown emits correct values without cancellation`() = runTest {
        val isCancelled = MutableStateFlow(false)
        val emittedValues = mutableListOf<Int>()

        startCountdownTimer(5, isCancelled)
            .onEach { emittedValues.add(it) }
            .launchIn(this)

        advanceTimeBy(6000L) // Simulate passage of 6 seconds
        assertEquals(listOf(5, 4, 3, 2, 1, 0), emittedValues)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `countdown stops when cancelled`() = runTest {
        val isCancelled = MutableStateFlow(false)
        val emittedValues = mutableListOf<Int>()

        launch {
            startCountdownTimer(5, isCancelled)
                .onEach { emittedValues.add(it) }
                .collect { }
        }

        advanceTimeBy(3000L) // Simulate 3 seconds
        isCancelled.value = true // Cancel countdown
        advanceTimeBy(3000L) // Simulate additional time

        assertEquals(listOf(5, 4, 3), emittedValues)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `countdown handles immediate cancellation`() = runTest {
        val isCancelled = MutableStateFlow(true)
        val emittedValues = mutableListOf<Int>()

        startCountdownTimer(5, isCancelled)
            .onEach { emittedValues.add(it) }
            .launchIn(this)

        advanceTimeBy(1000L) // Simulate 1 second
        assertTrue(emittedValues.isEmpty())
    }
}

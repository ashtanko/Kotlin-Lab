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
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class BasicUsageTest {

    @Test
    fun `basic usage test`() = runTest {
        val testFlow = flow {
            emit(1)
            emit(2)
            emit(3)
        }

        testFlow.test {
            assertEquals(1, awaitItem())
            assertEquals(2, awaitItem())
            assertEquals(3, awaitItem())
            awaitComplete() // Ensures the Flow completes
        }
    }

    @Test
    fun `emptyFlow test`() = runTest {
        emptyFlow<Int>().test {
            awaitComplete()
        }
    }

    @Test
    fun `error emission test`() = runTest {
        flow {
            emit(throw IllegalStateException("Test Error"))
        }.test {
            val error = awaitError()
            assertTrue(error is IllegalStateException)
            assertEquals("Test Error", error.message)
        }
    }
}

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

package dev.shtanko.concurrency.coroutines.job

import dev.shtanko.concurrency.TestBase
import dev.shtanko.concurrency.TestException
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.Job
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield
import org.junit.jupiter.api.Test

class JobTest : TestBase() {
    @Test
    fun `state test`() {
        val job = Job()
        assertTrue(job.isActive)
        job.cancel()
        assertTrue(!job.isActive)
    }

    @Test
    fun `handler test`() {
        val job = Job()
        var fireCount = 0
        job.invokeOnCompletion { fireCount++ }
        assertTrue(job.isActive)
        assertEquals(0, fireCount)
        // cancel once
        job.cancel()
        assertTrue(!job.isActive)
        assertEquals(1, fireCount)
        // cancel again
        job.cancel()
        assertTrue(!job.isActive)
        assertEquals(1, fireCount)
    }

    @Test
    fun `many handlers test`() {
        val job = Job()
        val num = 100
        val fireCount = IntArray(num)
        for (i in 0 until num) job.invokeOnCompletion { fireCount[i]++ }
        assertTrue(job.isActive)
        for (i in 0 until num) assertEquals(0, fireCount[i])
        // cancel once
        job.cancel()
        assertTrue(!job.isActive)
        for (i in 0 until num) assertEquals(1, fireCount[i])
        // cancel again
        job.cancel()
        assertTrue(!job.isActive)
        for (i in 0 until num) assertEquals(1, fireCount[i])
    }

    @Test
    fun `unregister in handler test`() {
        val job = Job()
        val num = 100
        val fireCount = IntArray(num)
        for (i in 0 until num) {
            var registration: DisposableHandle? = null
            registration = job.invokeOnCompletion {
                fireCount[i]++
                registration?.dispose()
            }
        }
        assertTrue(job.isActive)
        for (i in 0 until num) assertEquals(0, fireCount[i])
        // cancel once
        job.cancel()
        assertTrue(!job.isActive)
        for (i in 0 until num) assertEquals(1, fireCount[i])
        // cancel again
        job.cancel()
        assertTrue(!job.isActive)
        for (i in 0 until num) assertEquals(1, fireCount[i])
    }

    @Test
    fun `cancelled job test`() = runTest {
        expect(1)
        val job = launch {
            expectUnreached()
        }
        expect(2)
        job.cancelAndJoin()
        finish(3)
        assertTrue(job.isCompleted)
        assertTrue(!job.isActive)
        assertTrue(job.isCancelled)
    }

    @Test
    fun `failed job test`() = runTest(
        unhandled = listOf { it is TestException },
    ) {
        expect(1)
        val job = launch(NonCancellable) {
            expect(3)
            throw TestException()
        }
        expect(2)
        job.join()
        finish(4)
        assertTrue(job.isCompleted)
        assertTrue(!job.isActive)
        assertTrue(job.isCancelled)
    }

    @Test
    fun `failed child job test`() = runTest(
        unhandled = listOf { it is TestException },
    ) {
        expect(1)
        val job = launch(NonCancellable) {
            expect(3)
            launch {
                throw TestException()
            }
        }
        expect(2)
        job.join()
        finish(4)
        assertTrue(job.isCompleted)
        assertTrue(!job.isActive)
        assertTrue(job.isCancelled)
    }

    @Test
    fun `normal completion test`() = runTest {
        expect(1)
        val job = launch(start = CoroutineStart.LAZY) {
            expect(2)
            // launches child
            launch {
                expect(4)
            }
            // completes normally
        }
        // New job
        assertFalse(job.isActive)
        assertFalse(job.isCompleted)
        assertFalse(job.isCancelled)
        // New -> Active
        job.start()
        assertTrue(job.isActive)
        assertFalse(job.isCompleted)
        assertFalse(job.isCancelled)
        // Active -> Completing
        yield() // scheduled & starts child
        expect(3)
        assertTrue(job.isActive)
        assertFalse(job.isCompleted)
        assertFalse(job.isCancelled)
        // Completing -> Completed
        yield()
        finish(5)
        assertFalse(job.isActive)
        assertTrue(job.isCompleted)
        assertFalse(job.isCancelled)
    }
}

/*
 * Copyright 2021 Oleksii Shtanko
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

package dev.shtanko.concurrency.coroutines.async

import dev.shtanko.concurrency.TestBase
import dev.shtanko.concurrency.TestCancellationException
import dev.shtanko.concurrency.TestException
import dev.shtanko.utils.BadClass
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertSame
import kotlin.test.assertTrue
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.async
import kotlinx.coroutines.yield
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AsyncTest : TestBase() {

    @Test
    fun `test simple`() = runTest {
        expect(1)
        val job: Deferred<Int> = async {
            expect(3)
            42
        }
        expect(2)
        assertTrue(job.isActive)
        assertThat(job.await()).isEqualTo(42)
        assertTrue(job.isActive.not())
        expect(4)
        assertThat(job.await()).isEqualTo(42) // second await -- same result
        finish(5)
    }

    @Test
    fun `test undispatched`() = runTest {
        expect(1)
        val d: Deferred<Int> = async(start = CoroutineStart.UNDISPATCHED) {
            expect(2)
            42
        }
        expect(3)
        assertTrue(!d.isActive)
        assertEquals(d.await(), 42)
        finish(4)
    }

    @Test
    fun `test simple exception`() = runTest(expected = { it is TestException }) {
        expect(1)
        val d = async {
            finish(3)
            throw TestException()
        }
        expect(2)
        d.await() // will throw TestException
    }

    @Test
    fun `test cancellation with cause`() = runTest {
        expect(1)
        val d = async(NonCancellable, start = CoroutineStart.ATOMIC) {
            expect(3)
            yield()
        }
        expect(2)
        d.cancel(TestCancellationException("TEST"))
        try {
            d.await()
        } catch (e: TestCancellationException) {
            finish(4)
            assertEquals("TEST", e.message)
        }
    }

    @Test
    fun `test lost exception`() = runTest {
        expect(1)
        val deferred: Deferred<Nothing> = async(Job()) {
            expect(2)
            throw Exception()
        }

        // Exception is not consumed -> nothing is reported
        deferred.join()
        finish(3)
    }

    @Test
    fun `test parallel decomposition caught exception`() = runTest {
        val deferred = async(NonCancellable) {
            val decomposed = async(NonCancellable) {
                throw TestException()
            }
            try {
                decomposed.await()
            } catch (e: TestException) {
                e.printStackTrace()
                42
            }
        }
        assertEquals(42, deferred.await())
    }

    @Test
    fun `test parallel decomposition caught exception with inherited parent`() = runTest {
        expect(1)
        val deferred = async(NonCancellable) {
            expect(2)
            val decomposed = async { // inherits parent job!
                expect(3)
                throw TestException()
            }
            try {
                decomposed.await()
            } catch (e: TestException) {
                e.printStackTrace()
                expect(4) // Should catch this exception, but parent is already cancelled
                42
            }
        }
        try {
            // This will fail
            assertEquals(42, deferred.await())
        } catch (e: TestException) {
            e.printStackTrace()
            finish(5)
        }
    }

    @Test
    fun `starts the coroutine lazily`() = runTest {
        expect(1)
        val job = async(start = CoroutineStart.LAZY) {
            expect(3)
            42
        }
        expect(2)
        assertTrue(!job.isActive && !job.isCompleted)
        assertEquals(job.await(), 42)
        assertTrue(!job.isActive && job.isCompleted && !job.isCancelled)
        expect(4)
        assertEquals(job.await(), 42)
        expect(5)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun `atomically schedules the coroutine`() = runTest {
        expect(1)
        val job = async(start = CoroutineStart.ATOMIC) {
            expect(3)
            42
        }
        expect(2)
        assertTrue(job.isActive)
        assertFalse(job.isCompleted)
        assertEquals(job.await(), 42)
        expect(4)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun `immediately schedules the coroutine`() = runTest {
        expect(1)
        val job = async(start = CoroutineStart.DEFAULT) {
            expect(3)
            42
        }
        expect(2)
        assertTrue(job.isActive)
        assertFalse(job.isCompleted)
        assertEquals(job.await(), 42)
        expect(4)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun `start coroutine undispatched`() = runTest {
        expect(1)
        val job = async(start = CoroutineStart.UNDISPATCHED) {
            expect(2)
            42
        }
        expect(3)
        assertFalse(job.isActive)
        assertTrue(job.isCompleted)
        assertEquals(job.await(), 42)
        expect(4)
    }

    @Test
    fun `defer bad class test`() = runTest {
        val bad = BadClass()
        val d = async {
            expect(1)
            bad
        }
        assertSame(d.await(), bad)
        finish(2)
    }
}

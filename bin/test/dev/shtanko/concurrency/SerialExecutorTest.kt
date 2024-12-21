/*
 * Copyright 2024 Oleksii Shtanko
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

package dev.shtanko.concurrency

import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SerialExecutorTest {

    private lateinit var serialExecutor: SerialExecutor

    @BeforeEach
    fun setUp() {
        serialExecutor = SerialExecutor()
    }

    @Test
    fun executeMultipleTasksInOrder() {
        val latch1 = CountDownLatch(1)
        val latch2 = CountDownLatch(1)
        val latch3 = CountDownLatch(1)

        serialExecutor.execute {
            latch1.countDown() // Task 1
        }
        serialExecutor.execute {
            latch2.countDown() // Task 2
        }
        serialExecutor.execute {
            latch3.countDown() // Task 3
        }

        assertTrue(latch1.await(1, TimeUnit.SECONDS))
        assertTrue(latch2.await(1, TimeUnit.SECONDS))
        assertTrue(latch3.await(1, TimeUnit.SECONDS))

        assertTrue(latch1.count == 0L)
        assertTrue(latch2.count == 0L)
        assertTrue(latch3.count == 0L)
    }

    @Test
    fun executeTasksWithDelays() {
        val latch1 = CountDownLatch(1)
        val latch2 = CountDownLatch(1)

        serialExecutor.execute {
            Thread.sleep(100)
            latch1.countDown() // Task 1
        }
        serialExecutor.execute {
            latch2.countDown() // Task 2
        }

        assertTrue(latch1.await(1, TimeUnit.SECONDS))
        assertTrue(latch2.await(1, TimeUnit.SECONDS))

        assertTrue(latch1.count == 0L)
        assertTrue(latch2.count == 0L)
    }

    @Test
    fun executeTasksWithException() {
        val latch1 = CountDownLatch(1)
        val latch2 = CountDownLatch(1)

        serialExecutor.execute {
            latch1.countDown() // Task 1
            throw RuntimeException("Test Exception")
        }
        serialExecutor.execute {
            latch2.countDown() // Task 2
        }

        assertTrue(latch1.await(1, TimeUnit.SECONDS))
        assertTrue(latch2.await(1, TimeUnit.SECONDS))

        assertTrue(latch1.count == 0L)
        assertTrue(latch2.count == 0L)
    }

    @Test
    fun executeTasksAfterException() {
        val latch1 = CountDownLatch(1)
        val latch2 = CountDownLatch(1)
        val latch3 = CountDownLatch(1)

        serialExecutor.execute {
            latch1.countDown() // Task 1
            throw RuntimeException("Test Exception")
        }
        serialExecutor.execute {
            latch2.countDown() // Task 2
        }
        serialExecutor.execute {
            latch3.countDown() // Task 3
        }

        assertTrue(latch1.await(1, TimeUnit.SECONDS))
        assertTrue(latch2.await(1, TimeUnit.SECONDS))
        assertTrue(latch3.await(1, TimeUnit.SECONDS))

        assertTrue(latch1.count == 0L)
        assertTrue(latch2.count == 0L)
        assertTrue(latch3.count == 0L)
    }
}

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

import kotlin.concurrent.thread
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MyAtomicIntegerTest {

    @Test
    fun `increment and get returns incremented value`() {
        val atomicInt = MyAtomicInteger(0)
        assertEquals(1, atomicInt.incrementAndGet())
    }

    @Test
    fun `increment and get multiple times`() {
        val atomicInt = MyAtomicInteger(0)
        atomicInt.incrementAndGet()
        atomicInt.incrementAndGet()
        assertEquals(3, atomicInt.incrementAndGet())
    }

    @Test
    fun `increment and get with initial non-zero value`() {
        val atomicInt = MyAtomicInteger(10)
        assertEquals(11, atomicInt.incrementAndGet())
    }

    @Test
    fun `increment and get with large initial value`() {
        val atomicInt = MyAtomicInteger(Int.MAX_VALUE - 1)
        assertEquals(Int.MAX_VALUE, atomicInt.incrementAndGet())
    }

    @Test
    fun `increment and get with negative initial value`() {
        val atomicInt = MyAtomicInteger(-1)
        assertEquals(0, atomicInt.incrementAndGet())
    }

    @Test
    fun testMultiThreadIncrement() {
        val myAtomicInt = MyAtomicInteger(0)
        val threads = mutableListOf<Thread>()

        for (i in 1..100) {
            threads.add(
                thread {
                    for (j in 1..1000) {
                        myAtomicInt.incrementAndGet()
                    }
                },
            )
        }

        threads.forEach { it.join() }

        assertEquals(100000, myAtomicInt.get())
    }

    @Test
    fun testConcurrentIncrement() {
        val myAtomicInt = MyAtomicInteger(0)
        val threads = List(10) {
            thread {
                repeat(1000) {
                    myAtomicInt.incrementAndGet()
                }
            }
        }

        threads.forEach { it.join() }

        // Ensure atomic increments occurred without lost updates
        assertEquals(10000, myAtomicInt.get())
    }

    @Test
    fun testSingleCoroutineIncrement() = runBlocking {
        val myAtomicInt = MyAtomicInteger(0)
        val job1 = launch {
            repeat(1000) {
                myAtomicInt.incrementAndGet()
            }
        }

        job1.join()

        assertEquals(1000, myAtomicInt.get())
    }

    @Test
    fun testMultipleCoroutinesIncrement() = runBlocking {
        val myAtomicInt = MyAtomicInteger(0)
        val jobs = List(100) {
            launch {
                repeat(1000) {
                    myAtomicInt.incrementAndGet()
                }
            }
        }

        jobs.forEach { it.join() }

        assertEquals(100000, myAtomicInt.get())
    }

    @Test
    fun testConcurrentIncrementWithCoroutines() = runBlocking {
        val myAtomicInt = MyAtomicInteger(0)
        val jobs = List(10) {
            launch {
                repeat(1000) {
                    myAtomicInt.incrementAndGet()
                }
            }
        }

        jobs.forEach { it.join() }

        // Ensure atomic increments occurred without lost updates
        assertEquals(10000, myAtomicInt.get())
    }
}

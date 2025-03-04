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

@file:Suppress("MagicNumber")

package dev.shtanko.concurrency

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex

object Starvation {
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        val job1 = launch {
            while (true) {
                delay(100) // Simulate some work
            }
        }

        val job2 = launch {
            delay(500) // Wait for a short time
            println("Job 2 trying to acquire lock...")
            withLock(lock) {
                println("Job 2 acquired lock!")
                // Do some quick work
            }
        }

        job1.join()
        job2.join()
    }

    val lock = Mutex()

    suspend fun <T> withLock(lock: Mutex, block: suspend () -> T): T {
        lock.lock()
        try {
            return block()
        } finally {
            lock.unlock()
        }
    }
}

object StarvationThreads {

    @Volatile
    private var isRunning = true

    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        val t1 = Thread(Worker(), "Rich thread-1")
        val t2 = Thread(Worker(), "Rich thread-2")
        val t3 = Thread(Worker(), "Rich thread-3")

        val t4 = Thread(Worker(), "Starved thread-4")

        t1.priority = 10
        t2.priority = 8
        t3.priority = 5

        t4.priority = 1

        listOf(t1, t2, t3, t4).forEach {
            it.start()
        }
        Thread.sleep(1000L)
        isRunning = false
    }

    private class Worker : Runnable {
        private var counter = 0
        override fun run() {
            while (isRunning) {
                counter++
            }
            println("${Thread.currentThread().name} count before $counter")
        }
    }
}

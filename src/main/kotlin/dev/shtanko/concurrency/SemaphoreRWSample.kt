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

@file:Suppress("MagicNumber")

package dev.shtanko.concurrency

import java.util.concurrent.Semaphore
import java.util.concurrent.ThreadLocalRandom
import kotlin.concurrent.thread

object SemaphoreRWSample {
    @JvmStatic
    fun main(args: Array<String>) {
        val writeSemaphore: Semaphore = Semaphore(1)
        val readSemaphore: Semaphore = Semaphore(10)

        val pool = mutableListOf<Thread>()

        val resource = mutableListOf<Int>()

        repeat(1000) {
            var i = it + 1
            val writer = thread(start = false, name = "Write-Thread #$i") {
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextLong(1000))
                    writeSemaphore.acquire()
                    println("Hello form ${Thread.currentThread().name}")
                    resource.add(i)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } finally {
                    writeSemaphore.release()
                }
            }
            pool.add(writer)
        }

        repeat(1000) {
            var i = it + 1
            val writer = thread(start = false, name = "Read-Thread #$i") {
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextLong(1000))
                    readSemaphore.acquire()
                    println("Hello form ${Thread.currentThread().name}")
                    println("Resource size: ${resource.size}")
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } finally {
                    readSemaphore.release()
                }
            }
            pool.add(writer)
        }

        pool.forEach(Thread::start)
        pool.forEach { it.join() }

        println("Final resource size: ${resource.size}")
    }
}

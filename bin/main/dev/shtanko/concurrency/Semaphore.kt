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

import java.util.concurrent.Semaphore
import kotlin.random.Random

const val MIN_PROCESSING_TIME = 1000L
const val MAX_PROCESSING_TIME = 3000L

fun main() {
    val semaphore = Semaphore(3) // Initialize semaphore with permits

    val data = (1..10).toList() // Simulating a large amount of data

    val threads = mutableListOf<Thread>()

    for (item in data) {
        val thread = Thread {
            try {
                semaphore.acquire() // Acquire permit
                println("Thread ${Thread.currentThread().id} processing item $item")
                processItem(item)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            } finally {
                semaphore.release() // Release permit
                println("Thread ${Thread.currentThread().id} released permit")
            }
        }
        threads.add(thread)
        thread.start()
    }

    threads.forEach { it.join() }
}

fun processItem(item: Int) {
    // Simulating processing time
    Thread.sleep(Random.nextLong(MIN_PROCESSING_TIME, MAX_PROCESSING_TIME))
    println("Item $item processed")
}

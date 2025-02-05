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

import java.util.concurrent.Executors
import java.util.concurrent.LinkedTransferQueue
import java.util.concurrent.TimeUnit

/**
 * LinkedTransferQueue is a concurrent queue implementation in Java that supports additional operations such as
 * transferring elements between threads. It’s part of the java.util.concurrent package and is designed for scenarios
 * where you need a high-performance, thread-safe, and non-blocking queue with advanced capabilities.
 */
@Suppress("MagicNumber")
object LinkedTransferQueueExample {
    @JvmStatic
    fun main(args: Array<String>) {
        val queue = LinkedTransferQueue<String>()
        val executor = Executors.newFixedThreadPool(2) // Create a thread pool with 2 threads

        // Producer thread that adds elements to the queue
        val producer = Runnable {
            try {
                for (i in 1..5) {
                    val item = "Item $i"
                    println("Producer: Adding $item")
                    queue.offer(item) // Offer item to the queue
                    Thread.sleep(500) // Simulate work
                }
            } catch (e: InterruptedException) {
                Thread.currentThread().interrupt()
            }
        }

        // Consumer thread that takes elements from the queue
        val consumer = Runnable {
            try {
                while (true) {
                    val item = queue.poll(1, TimeUnit.SECONDS) // Poll item from the queue with a timeout
                    if (item != null) {
                        println("Consumer: Processing $item")
                        Thread.sleep(1000) // Simulate work
                    } else {
                        println("Consumer: No more items, exiting.")
                        break
                    }
                }
            } catch (e: InterruptedException) {
                Thread.currentThread().interrupt()
            }
        }

        // Submit tasks to the executor
        executor.submit(producer)
        executor.submit(consumer)

        // Shutdown executor
        executor.shutdown()
        try {
            if (!executor.awaitTermination(1, TimeUnit.MINUTES)) {
                executor.shutdownNow()
            }
        } catch (e: InterruptedException) {
            executor.shutdownNow()
        }
    }
}

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

import java.util.LinkedList
import java.util.Queue
import kotlin.concurrent.thread

/**
 * Problem: In the producer-consumer problem, one or more producer threads generate data and put it into a buffer,
 * while one or more consumer threads take data from the buffer. Proper synchronization is required to avoid conflicts
 * and ensure smooth data flow.
 */
object ProducerConsumerExample {

    private val buffer: Queue<Int> = LinkedList()
    private const val BUFFER_SIZE = 10
    private val lock = Object()
    private var produced = 0

    fun producer() {
        while (true) {
            synchronized(lock) {
                while (buffer.size == BUFFER_SIZE) {
                    lock.wait()
                }
                buffer.add(produced++)
                lock.notifyAll()
                println("Produced: ${buffer.last()}")
            }
            Thread.sleep(100)
        }
    }

    fun consumer() {
        while (true) {
            synchronized(lock) {
                while (buffer.isEmpty()) {
                    lock.wait()
                }
                val value = buffer.remove()
                lock.notifyAll()
                println("Consumed: $value")
            }
            Thread.sleep(150)
        }
    }

    fun run() {
        val producerThread = thread { producer() }
        val consumerThread = thread { consumer() }

        producerThread.join()
        consumerThread.join()
    }

    @JvmStatic
    fun main(args: Array<String>) = run()
}

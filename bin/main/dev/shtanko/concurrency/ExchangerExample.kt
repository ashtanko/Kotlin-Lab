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

import java.util.concurrent.Exchanger

/**
 * `Exchanger`: Allows two threads to exchange objects at a synchronization point. Each thread presents an object and
 * receives the object from the other thread.
 */
object ExchangerExample {
    @JvmStatic
    fun main(args: Array<String>) {
        val exchanger = Exchanger<String>()

        // Thread 1: Produces data
        val producerThread = Thread {
            try {
                val dataToSend = "Hello from producer"
                println("Producer: Sending data")
                val receivedData = exchanger.exchange(dataToSend)
                println("Producer: Received data: $receivedData")
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }

        // Thread 2: Consumes data
        val consumerThread = Thread {
            try {
                val receivedData = exchanger.exchange(null)
                println("Consumer: Received data: $receivedData")
                val responseData = "Hello from consumer"
                println("Consumer: Sending response")
                exchanger.exchange(responseData)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }

        producerThread.start()
        consumerThread.start()

        producerThread.join()
        consumerThread.join()
    }
}

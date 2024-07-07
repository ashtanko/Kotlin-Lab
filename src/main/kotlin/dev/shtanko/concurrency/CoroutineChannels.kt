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

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    // Create a channel of integers with a buffer size of 3
    val channel = Channel<Int>(capacity = 3)

    // Launch a coroutine to send data
    launch {
        repeat(5) {
            println("Sending $it")
            channel.send(it)
        }
        channel.close() // Close the channel when done sending
    }

    // Receive data from the channel
    for (value in channel) {
        println("Received $value")
    }

    println("Done receiving")
}

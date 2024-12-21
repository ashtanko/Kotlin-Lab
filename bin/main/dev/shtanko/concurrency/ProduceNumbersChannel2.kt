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

import kotlin.random.Random
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// Function to generate random numbers and send them to a channel
@Suppress("MagicNumber")
fun produceNumbers(channel: SendChannel<Int>) = GlobalScope.launch {
    repeat(10) {
        val number = Random.nextInt(1, 101) // Generate a random number
        channel.send(number)
        delay(Random.nextLong(100, 501)) // Simulate varying processing time
    }
    channel.close() // Close the channel when done sending
}

// Function to square numbers received from a channel and send them to another channel
@Suppress("MagicNumber")
fun squareNumbers(input: ReceiveChannel<Int>, output: SendChannel<Int>) = GlobalScope.launch {
    for (x in input) {
        val squared = x * x // Square the number
        output.send(squared)
        delay(Random.nextLong(100, 501)) // Simulate varying processing time
    }
    output.close() // Close the output channel when done sending
}

// Function to filter numbers based on a predicate and send them to another channel
@Suppress("MagicNumber")
fun filterNumbers(input: ReceiveChannel<Int>, output: SendChannel<Int>, predicate: (Int) -> Boolean) =
    GlobalScope.launch {
        for (x in input) {
            if (predicate(x)) {
                output.send(x)
            }
            delay(Random.nextLong(100, 501)) // Simulate varying processing time
        }
        output.close() // Close the output channel when done sending
    }

// Function to print results received from a channel
@Suppress("MagicNumber")
fun printResults2(results: ReceiveChannel<Int>) = GlobalScope.launch {
    for (result in results) {
        println("Result: $result")
        delay(Random.nextLong(100, 501)) // Simulate varying processing time
    }
}

@Suppress("MagicNumber")
fun main() = runBlocking<Unit> {
    // Create channels
    val numbersChannel = Channel<Int>() // Unbuffered channel

    // Launch multiple producers dynamically
    repeat(3) {
        launch { produceNumbers(numbersChannel) }
    }

    // Create channels for processing stages
    val squaredNumbersChannel = Channel<Int>() // Unbuffered channel
    val filteredNumbersChannel = Channel<Int>() // Unbuffered channel

    // Launch multiple consumers dynamically
    val consumers = List(4) { range ->
        when (range) {
            in 0..1 -> launch { squareNumbers(numbersChannel, squaredNumbersChannel) }
            in 2..3 -> launch { filterNumbers(squaredNumbersChannel, filteredNumbersChannel) { it % 2 == 0 } }
            else -> launch { printResults2(filteredNumbersChannel) }
        }
    }

    // Delay for some time to allow processing
    delay(5000)

    // Cancel all coroutines to clean up
    numbersChannel.cancel()
    squaredNumbersChannel.cancel()
    filteredNumbersChannel.cancel()
    consumers.forEach { it.cancel() }
}

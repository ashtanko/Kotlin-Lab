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

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// Function to produce a sequence of numbers
@Suppress("MagicNumber")
fun produceNumbers(): ReceiveChannel<Int> = GlobalScope.produce {
    var x = 1
    while (true) {
        send(x++)
        delay(100) // Simulate some processing time
    }
}

// Function to square numbers received
@Suppress("MagicNumber")
fun squareNumbers(numbers: ReceiveChannel<Int>): ReceiveChannel<Int> = GlobalScope.produce {
    for (x in numbers) {
        send(x * x)
        delay(200) // Simulate some processing time
    }
}

// Function to print the results
fun printResults(results: ReceiveChannel<Int>) = GlobalScope.launch {
    for (result in results) {
        println("Result: $result")
    }
}

@Suppress("MagicNumber")
fun main() = runBlocking {
    // Create channels
    val numbers = produceNumbers()
    val squaredNumbers = squareNumbers(numbers)

    // Print the squared results
    printResults(squaredNumbers)

    // Delay for some time to allow processing
    delay(1000)

    // Cancel all coroutines to clean up
    numbers.cancel()
    squaredNumbers.cancel()
}

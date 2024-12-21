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

package dev.shtanko.concurrency.coroutines

import kotlin.also
import kotlin.collections.forEachIndexed
import kotlin.let
import kotlin.ranges.until
import kotlin.text.toInt
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Represents a consumer that consumes messages from a given channel.
 *
 * @property msgs The channel from which messages are consumed.
 */
class Consumer(private val msgs: Channel<Int>) {
    /**
     * Consumes messages from the channel until it's closed.
     */
    suspend fun consume() {
        println("consume: Started")
        for (msg in msgs) {
            println("consume: Received: $msg")
        }
    }
}

/**
 * Represents a producer that produces messages to a given channel.
 *
 * @property msgs The channel to which messages are sent.
 * @property done A CompletableDeferred to signal completion of message production.
 */
class Producer(private val msgs: Channel<Int>, private val done: CompletableDeferred<Boolean>) {
    /**
     * Produces a specified number of messages to the channel.
     *
     * @param max The maximum number of messages to produce.
     */
    suspend fun produce(max: Int) {
        println("produce: Started")
        for (i in 0 until max) {
            println("produce: Sending $i")
            msgs.send(i)
        }
        done.complete(true) // signal when done
        println("produce: Done")
    }
}

/**
 * The main function that sets up and runs the producer and consumer coroutines.
 *
 * @param args Command line arguments for runtime configuration.
 */
fun main(args: Array<String>) {
    var cpuprofile: String? = null
    var memprofile: String? = null
    var maxMessages = 5

    // Parse flags
    args.forEachIndexed { index, arg ->
        when (arg) {
            "--cpuprofile" -> if (index + 1 < args.size) cpuprofile = args[index + 1]
            "--memprofile" -> if (index + 1 < args.size) memprofile = args[index + 1]
            "--n" -> if (index + 1 < args.size) maxMessages = args[index + 1].toInt()
        }
    }

    // Utilize the max number of cores available
    Runtime.getRuntime().availableProcessors().also { cores ->
        println("Utilizing $cores cores")
    }

    // Channels for communication
    val msgs = Channel<Int>()
    val done = CompletableDeferred<Boolean>()

    // Start the producer and consumer coroutines
    runBlocking {
        launch { Producer(msgs, done).produce(maxMessages) }
        launch { Consumer(msgs).consume() }
        done.await() // Finish the program when the production is done
    }

    // Memory Profile (placeholder, as JVM profiling is different from Go's pprof)
    memprofile?.let {
        println("Memory profiling is not directly supported in this example.")
    }
}

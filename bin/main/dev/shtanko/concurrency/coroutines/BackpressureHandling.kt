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

package dev.shtanko.concurrency.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

object BackpressureHandling {
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        println("With buffer:")
        // Use buffer to allow the producer to emit data faster than it is consumed
        fastEmittingFlow()
            .buffer() // Buffers emitted data
            .collect { value ->
                slowConsumer(value)
            }

        println("\nWith conflate:")
        // Use conflate to only keep the most recent emitted data
        fastEmittingFlow()
            .conflate() // Only the most recent item is kept in memory
            .collect { value ->
                slowConsumer(value)
            }
    }

    // Simulating a producer that emits data rapidly
    fun fastEmittingFlow(): Flow<Int> = flow {
        repeat(20) {
            delay(50L) // Simulating rapid emission (every 100ms)
            emit(it) // Emit random numbers
        }
    }

    // Simulating a slower consumer (e.g., taking 300ms to process each item)
    suspend fun slowConsumer(value: Int) {
        delay(300L) // Simulate slow processing
        println("Processed: $value")
    }
}

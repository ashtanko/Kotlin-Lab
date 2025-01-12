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

@file:Suppress("MagicNumber", "TooGenericExceptionThrown")

package dev.shtanko.concurrency.coroutines

import kotlin.random.Random
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.runBlocking

// Simulate a network call that randomly fails
private fun simulateNetworkCall(): Flow<String> = flow {
    // Randomly decide if the call should succeed or fail
    if (Random.nextBoolean()) {
        throw Exception("Network error occurred")
    } else {
        emit("Network call successful")
    }
}

object RetryingFailedNetworkCalls {
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        // Retry logic for the network call, up to 3 retries
        simulateNetworkCall()
            .retry(3) { cause ->
                // Retry if the exception is a network error (simulated by a random failure)
                if (cause is Exception) {
                    println("Retrying due to: ${cause.message}")
                    delay(1000L) // Simulate some delay before retrying
                    true // Return true to indicate that retry should happen
                } else {
                    false
                }
            }
            .catch { e ->
                println("Network call failed after retries: ${e.message}")
            }
            .collect { success ->
                println(success) // Print the success message if the call eventually succeeds
            }
    }
}

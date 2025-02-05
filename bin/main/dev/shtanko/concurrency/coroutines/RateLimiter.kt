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
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Semaphore

class RateLimiter(private val maxCallsPerSecond: Int) {
    private val semaphore = Semaphore(maxCallsPerSecond)

    suspend fun <T> withRateLimit(block: suspend () -> T): T {
        semaphore.acquire()
        return try {
            block()
        } finally {
            delay(1000L / maxCallsPerSecond)
            semaphore.release()
        }
    }
}

private suspend fun emulateFetchData(): String {
    delay(400)  // Simulate a network delay
    return "Fetched data"
}

object RateLimiterDemo {
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        val rateLimiter = RateLimiter(5)  // Allow up to 5 calls per second
        repeat(100) {
            launch {
                val result = rateLimiter.withRateLimit { emulateFetchData() }
                println(result)
            }
        }
    }
}

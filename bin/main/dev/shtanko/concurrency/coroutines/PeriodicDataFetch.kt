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

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun fetchDataPeriodically(): Flow<String> = flow {
    while (true) {
        emit("Fetched data at ${System.currentTimeMillis()}")
        delay(5000L) // Wait for 5 seconds before fetching data again
    }
}

object PeriodicDataFetch {
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        // Launching a coroutine scope that will automatically cancel when the scope is destroyed
        val job = launch {
            fetchDataPeriodically().collect { data ->
                println(data)
            }
        }

        // Let it run for a while before cancelling
        delay(15000L) // Run for 15 seconds to simulate periodic fetching
        job.cancelAndJoin() // Cancel the job and stop the periodic fetching

        println("Fetching stopped.")
    }
}

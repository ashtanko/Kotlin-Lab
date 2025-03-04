/*
 * Designed and developed by 2025 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
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
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

object CachingWithSharedFlow {
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        val cache = MutableSharedFlow<String>(replay = 1)
        // Simulate a collector that listens for updates
        val job = launch {
            cache.collect { data ->
                println("Cached data: $data")
            }
        }
        // Update the cache with new data
        cache.emit("Initial Data")
        delay(500L) // Simulate some delay between updates
        cache.emit("Updated Data")
        delay(500L)
        cache.emit("Final Data")

        // Cancel the collector job and complete the coroutine
        job.cancelAndJoin()
        println("Collector stopped")
    }
}

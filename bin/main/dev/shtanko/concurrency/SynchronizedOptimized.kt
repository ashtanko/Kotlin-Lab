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

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.newFixedThreadPoolContext
import kotlinx.coroutines.runBlocking

class SynchronizedOptimizedIncrementor {
    var sharedCounter: Int = 0
        private set

    // Method to update the counter if the condition is met
    fun updateCounterIfNecessary(shouldIActuallyIncrement: Boolean) {
        if (shouldIActuallyIncrement) {
            synchronized(this) {
                // only locks when needed, using the Increment's instance as the lock.
                sharedCounter++
            }
        }
    }
}

@Suppress("MagicNumber")
fun main() = runBlocking {
    val incrementor = SynchronizedOptimizedIncrementor()
    // Create a CoroutineScope with a fixed thread pool of 4 threads
    val scope = CoroutineScope(newFixedThreadPoolContext(4, "synchronizationPool"))
    scope.launch {
        // Create 1000 coroutines (light-weight threads)
        val coroutines = 1.rangeTo(1000).map {
            launch {
                // In each coroutine, increment the sharedCounter 1000 times if the condition is met
                for (i in 1..1000) {
                    incrementor.updateCounterIfNecessary(it % 2 == 0)
                }
            }
        }
        // Wait for all coroutines to finish their jobs
        coroutines.forEach { coroutine ->
            coroutine.join()
        }
    }.join()
    // Print the final value of sharedCounter
    println("The number of shared counter is ${incrementor.sharedCounter}")
}

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

import java.util.concurrent.locks.ReentrantLock
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.newFixedThreadPoolContext
import kotlinx.coroutines.runBlocking

private class LockIncrementor {
    private val sharedCounterLock = ReentrantLock()
    var sharedCounter: Int = 0
        private set

    fun updateCounterIfNecessary(shouldIActuallyIncrement: Boolean) {
        if (shouldIActuallyIncrement) {
            try {
                sharedCounterLock.lock()
                sharedCounter++
            } finally {
                sharedCounterLock.unlock()
            }
        }
    }
}

@Suppress("MagicNumber")
fun main() = runBlocking {
    val incrementor = LockIncrementor()
    val scope =
        CoroutineScope(
            newFixedThreadPoolContext(
                Runtime.getRuntime().availableProcessors(),
                "synchronizationPool",
            ),
        ) // We want our code to run on 4 threads
    scope.launch {
        val coroutines = 1.rangeTo(1000).map {
            // create 1000 coroutines (light-weight threads).
            launch {
                for (i in 1..1000) { // and in each of them, increment the sharedCounter 1000 times.
                    incrementor.updateCounterIfNecessary(it % 2 == 0)
                }
            }
        }

        coroutines.forEach { coroutine ->
            coroutine.join() // wait for all coroutines to finish their jobs.
        }
    }.join()

    println("The number of shared counter is ${incrementor.sharedCounter}")
}

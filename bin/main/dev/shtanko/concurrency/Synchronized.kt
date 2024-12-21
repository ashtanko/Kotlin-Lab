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

class SynchronizedIncrementor {
    var sharedCounter = 0

    @Synchronized
    fun updateCounter() {
        sharedCounter++
    }
}

@Suppress("MagicNumber")
fun main() = runBlocking {
    val incrementor = SynchronizedIncrementor()
    val scope =
        CoroutineScope(newFixedThreadPoolContext(5, "synchronizationPool")) // We want our code to run on 5 threads
    scope.launch {
        val coroutines = 1.rangeTo(1000).map { // create 1000 coroutines (light-weight threads).
            launch {
                for (i in 1..1000) { // and in each of them, increment the sharedCounter 1000 times.
                    incrementor.updateCounter() // call the newly created function that is now synchronized
                }
            }
        }

        coroutines.forEach { coroutine ->
            coroutine.join() // wait for all coroutines to finish their jobs.
        }
    }.join()

    println("The number of shared counter is ${incrementor.sharedCounter}")
}

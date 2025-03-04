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

package dev.shtanko.concurrency.coroutines

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

object SuspendAndMutex {
    private val mutex = Mutex()
    private var counter = 0

    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        val jobs = List(100) {
            launch {
                incrementCounter()
            }
        }
        jobs.forEach { it.join() }
    }

    suspend fun incrementCounter() {
        mutex.withLock {
            counter++
            println("Counter: $counter")
        }
    }
}

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

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Suppress("MagicNumber")
fun main() = runBlocking {
    // Launching a coroutine and getting its Job
    val job = launch {
        repeat(1000) { i ->
            println("Job: I'm sleeping $i ...")
            delay(500L)
        }
    }

    delay(1300L) // Delay a bit
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // Cancels the job and waits for its completion
    println("main: Now I can quit.")
}

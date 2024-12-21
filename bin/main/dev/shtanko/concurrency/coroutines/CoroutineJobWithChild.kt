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

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Suppress("MagicNumber")
fun main() = runBlocking {
    // Creating a parent job
    val parentJob = Job()

    // Creating a coroutine scope with the parent job
    val scope = CoroutineScope(parentJob + Dispatchers.Default)

    // Launching a child coroutine
    val childJob = scope.launch {
        repeat(1000) { i ->
            println("Child: I'm sleeping $i ...")
            delay(500L)
        }
    }

    // Launching another child coroutine
    scope.launch {
        repeat(1000) { i ->
            println("Another Child: I'm working $i ...")
            delay(300L)
        }
    }

    delay(1300L) // Delay a bit
    println("main: I'm tired of waiting!")
    parentJob.cancelChildren() // Cancels all child jobs
    println("main: Now I can quit.")
}

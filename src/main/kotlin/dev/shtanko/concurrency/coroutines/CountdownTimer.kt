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

import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun startCountdownTimer(start: Int, isCancelled: StateFlow<Boolean>): Flow<Int> = flow {
    for (i in start downTo 0) {
        if (isCancelled.value) {
            println("Countdown cancelled")
            break
        }
        emit(i)
        delay(1.seconds)
    }
}

object CountdownTimer {
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        val isCancelled = MutableStateFlow(false)

        val job = launch {
            startCountdownTimer(10, isCancelled).collect { value ->
                println("Countdown: $value")
            }
        }

        // Simulate cancellation after 5 seconds
        delay(5000L)
        isCancelled.value = true

        job.cancelAndJoin() // Wait for the countdown coroutine to complete
        println("Main coroutine finished")
    }
}

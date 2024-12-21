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

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Suppress("MagicNumber")
fun main() = runBlocking {
    val flow = flow {
        emit(1)
        delay(100)
        emit(2)
        delay(100)
        emit(3)
    }

    val job = launch {
        try {
            flow.collect { value ->
                println(value)
                if (value == 2) cancel() // Cancelling the flow collection
            }
        } catch (e: CancellationException) {
            println("Flow collection cancelled: ${e.message}")
            e.printStackTrace() // Log the exception
            throw e // Optionally rethrow the exception if you want to propagate it
        }
    }

    delay(250) // Delay to allow some elements to be emitted
    job.cancelAndJoin() // Cancelling the job collecting the flow
}

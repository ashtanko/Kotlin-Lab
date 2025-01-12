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

@file:Suppress("MagicNumber", "SwallowedException")

package dev.shtanko.concurrency.coroutines

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking

object SingleExample {
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        // Example 1: Flow with a single value
        try {
            val result = getSingleValueFlow().single() // Collect the single value
            println("Received single value: $result")
        } catch (e: NoSuchElementException) {
            println("Flow did not emit a single value")
        }

        // Example 2: Flow with multiple values (will throw exception)
        try {
            val result = getMultipleValuesFlow().single() // This will throw an exception
            println("Received single value: $result")
        } catch (e: IllegalArgumentException) {
            println("Flow emitted more than one value: ${e.message}")
        }
    }

    private fun getSingleValueFlow(): Flow<Int> = flow {
        emit(42) // This flow emits exactly one value
    }

    private fun getMultipleValuesFlow(): Flow<Int> = flow {
        emit(1)
        emit(2) // This flow emits two values
    }
}

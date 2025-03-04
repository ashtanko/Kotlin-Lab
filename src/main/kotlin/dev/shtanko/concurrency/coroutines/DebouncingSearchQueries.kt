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

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

fun userInputFlow(inputs: List<String>, interval: Long): Flow<String> = flow {
    for (input in inputs) {
        emit(input)
        delay(interval) // Simulates the user typing delay
    }
}

@OptIn(FlowPreview::class)
fun searchFeature(inputFlow: Flow<String>): Flow<String> =
    inputFlow
        .debounce(100L) // Wait 100ms for the latest query
        .distinctUntilChanged() // Only act on unique queries

object DebouncingSearchQueries {
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        val userInput = listOf("k", "ko", "kot", "kotlin")

        val searchFlow = searchFeature(userInputFlow(userInput, 200L)) // Typing with 200ms interval

        searchFlow.collect { query ->
            println("Searching for: $query")
        }
        delay(3000L)
    }
}

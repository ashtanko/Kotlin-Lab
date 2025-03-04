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

package dev.shtanko.concurrency.coroutines.flow.operators

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.flow.withIndex
import kotlinx.coroutines.runBlocking

object FlowTransformingOperators {

    /**
     * Transforms each emitted value.
     */
    object MapExample {
        @JvmStatic
        fun main(args: Array<String>) = runBlocking {
            val flow = (1..5).asFlow()
                .map { it * 2 } // Multiply each value by 2

            flow.collect { value ->
                println(value) // Output: 2, 4, 6, 8, 10
            }
        }
    }

    /**
     * Transforms values, excluding null results.
     */
    object MapNotNullExample {
        @JvmStatic
        fun main(args: Array<String>) = runBlocking {
            val flow = flowOf(1, 2, null, 4, null)
                .mapNotNull { it?.let { it * 2 } } // Multiply non-null values by 2

            flow.collect { value ->
                println(value) // Output: 2, 4, 8
            }
        }
    }

    /**
     * Applies a transformation with an index.
     */
    object MapIndexedExample {
        @JvmStatic
        fun main(args: Array<String>) = runBlocking {
            val flow = listOf("a", "b", "c").asFlow()
                .withIndex().map { value -> "${value.index}: ${value.value}" }

            flow.collect { value ->
                println(value) // Output: "0: a", "1: b", "2: c"
            }
        }
    }

    /**
     * Emits multiple values for each upstream value.
     */
    object TransformExample {
        @JvmStatic
        fun main(args: Array<String>) = runBlocking {
            val flow = (1..3).asFlow()
                .transform { value ->
                    emit("Start of $value")
                    emit(value * value) // Emit square of the value
                    emit("End of $value")
                }

            flow.collect { value ->
                println(value)
                // Output:
                // Start of 1
                // 1
                // End of 1
                // Start of 2
                // 4
                // End of 2
                // Start of 3
                // 9
                // End of 3
            }
        }
    }

    /**
     * Maps each value to a new flow and concatenates them.
     */
    object FlatMapConcatExample {
        @OptIn(ExperimentalCoroutinesApi::class)
        @JvmStatic
        fun main(args: Array<String>) = runBlocking {
            val flow = (1..3).asFlow()
                .flatMapConcat { value ->
                    flowOf(value, value * 10, value + 1) // For each value, emit two values
                }

            flow.collect { value ->
                println(value)
                // Output: 1, 10, 2, 20, 3, 30
            }
        }
    }

    /**
     * Maps each value to a flow and merges them concurrently.
     */
    object FlatMapMergeExample {
        @OptIn(ExperimentalCoroutinesApi::class)
        @JvmStatic
        fun main(args: Array<String>) = runBlocking {
            val flow = (1..3).asFlow()
                .flatMapMerge { value ->
                    flow {
                        emit("$value start")
                        delay(100)
                        emit("$value end")
                    }
                }

            flow.collect { value ->
                println(value)
                // Output (order may vary):
                // 1 start, 2 start, 3 start, 1 end, 2 end, 3 end
            }
        }
    }

    /**
     * Switches to the latest flow on each new emission, canceling the previous one.
     */
    object FlatMapLatestExample {
        @OptIn(ExperimentalCoroutinesApi::class)
        @JvmStatic
        fun main(args: Array<String>) = runBlocking {
            val flow = (1..3).asFlow()
                .flatMapLatest { value ->
                    flow {
                        emit("$value start")
                        delay(100) // Only the last emission completes
                        emit("$value end")
                    }
                }

            flow.collect { value ->
                println(value)
                // Output:
                // 1 start, 2 start, 3 start, 3 end
            }
        }
    }
}

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

package dev.shtanko.concurrency.coroutines.flow.operators

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.dropWhile
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.runBlocking

object FlowFilteringOperators {

    /**
     * Emits only values that satisfy a given predicate.
     */
    object FilterExample {
        @JvmStatic
        fun main(args: Array<String>) = runBlocking {
            val flow = (1..10).asFlow()
                .filter { it % 2 == 0 } // Only even numbers

            flow.collect { value ->
                println(value) // Output: 2, 4, 6, 8, 10
            }
        }
    }

    /**
     * Emits values that do not satisfy a given predicate.
     */
    object FilterNotExample {
        @JvmStatic
        fun main(args: Array<String>) = runBlocking {
            val flow = (1..10).asFlow()
                .filterNot { it % 2 == 0 } // Exclude even numbers

            flow.collect { value ->
                println(value) // Output: 1, 3, 5, 7, 9
            }
        }
    }

    /**
     * Filters out null values from the flow.
     */
    object FilterNotNullExample {
        @JvmStatic
        fun main(args: Array<String>) = runBlocking {
            val flow = flowOf(1, null, 2, null, 3)
                .filterNotNull() // Exclude null values

            flow.collect { value ->
                println(value) // Output: 1, 2, 3
            }
        }
    }

    /**
     * Filters out values that are not instances of the specified type.
     */
    object FilterIsInstanceExample {
        @JvmStatic
        fun main(args: Array<String>) = runBlocking {
            val flow = flowOf(1, "2").filterIsInstance<Int>()
            flow.collect {
                println(it)
            }
        }
    }

    /**
     * Skips the first N elements from the flow.
     */
    object DropExample {
        @JvmStatic
        fun main(args: Array<String>) = runBlocking {
            val flow = (1..10).asFlow()
                .drop(5) // Skip the first 5 elements

            flow.collect { value ->
                println(value) // Output: 6, 7, 8, 9, 10
            }
        }
    }

    /**
     * Skips elements while the predicate is true.
     */
    object DropWhileExample {
        @JvmStatic
        fun main(args: Array<String>) = runBlocking {
            val flow = (1..10).asFlow()
                .dropWhile { it < 5 } // Skip elements less than 5

            flow.collect { value ->
                println(value) // Output: 5, 6, 7, 8, 9, 10
            }
        }
    }

    /**
     * Emits only the first N elements from the flow.
     */
    object TakeExample {
        @JvmStatic
        fun main(args: Array<String>) = runBlocking {
            val flow = (1..10).asFlow()
                .take(3) // Take the first 3 elements

            flow.collect { value ->
                println(value) // Output: 1, 2, 3
            }
        }
    }

    /**
     * Emits elements while the predicate is true.
     */
    object TakeWhile {
        @JvmStatic
        fun main(args: Array<String>) = runBlocking {
            val flow = (1..10).asFlow()
                .takeWhile { it < 5 } // Take elements less than 5

            flow.collect { value ->
                println(value) // Output: 1, 2, 3, 4
            }
        }
    }
}

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

package dev.shtanko.concurrency.coroutines.flow.operators

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flattenConcat
import kotlinx.coroutines.flow.flattenMerge
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking

object FlowCombiningOperators {
    /**
     * Combines the latest values from two flows.
     * The resulting flow will emit every time one of the flows emits a value.
     */
    object CombineExample {
        @JvmStatic
        fun main(args: Array<String>) = runBlocking {
            val flow1 = flowOf(1, 2, 3, 4, 5, 6).onEach { delay(10L) }
            val flow2 = flowOf("a", "b", "c", "d", "e").onEach { delay(20L) }
            combine(flow1, flow2) { i, s -> i.toString() + s }
                .collect {
                    println(it)
                }
        }
    }

    /**
     * Combines the latest values from two flows.
     * The resulting flow will only emit when both flows have emitted at least one value.
     */
    object ZipExample {
        @JvmStatic
        fun main(args: Array<String>) = runBlocking {
            val flow1 = (1..4).asFlow()
            val flow2 = flowOf("A", "B", "C", "D")

            flow1.zip(flow2) { num, letter ->
                "$num$letter"
            }.collect { value ->
                println(value)
                // Output:
                // 1A
                // 2B
                // 3C
            }
        }
    }

    /**
     * Merges values from two flows into a single flow.
     */
    object MergeExample {
        @JvmStatic
        fun main(args: Array<String>) = runBlocking {
            val flow1 = (1..3).asFlow().onEach { delay(100) }
            val flow2 = flowOf("A", "B", "C").onEach { delay(150) }

            merge(flow1, flow2).collect { value ->
                println(value)
                // Output (interleaved):
                // 1
                // A
                // 2
                // B
                // 3
                // C
            }
        }
    }

    /**
     * Flattens a flow of flows into a single flow.
     */
    object FlattenConcatExample {
        @OptIn(ExperimentalCoroutinesApi::class)
        @JvmStatic
        fun main(args: Array<String>) = runBlocking {
            val flowOfFlows = flowOf(
                flowOf(1, 2, 3).onEach { delay(100) },
                flowOf(4, 5, 6),
            )

            flowOfFlows.flattenConcat().collect { value ->
                println(value)
                // Output:
                // 1
                // 2
                // 3
                // 4
                // 5
                // 6
            }
        }
    }

    object FlattenMergeExample {
        @OptIn(ExperimentalCoroutinesApi::class)
        @JvmStatic
        fun main(args: Array<String>) = runBlocking {
            val flowOfFlows = flowOf(
                flowOf(1, 2, 3).onEach { delay(100) },
                flowOf(4, 5, 6).onEach { delay(50) },
            )

            flowOfFlows.flattenMerge().collect { value ->
                println(value)
                // Output (interleaved):
                // 4
                // 1
                // 5
                // 2
                // 6
                // 3
            }
        }
    }
}

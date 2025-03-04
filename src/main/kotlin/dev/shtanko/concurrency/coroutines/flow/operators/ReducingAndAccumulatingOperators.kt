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

import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.runningFold
import kotlinx.coroutines.flow.runningReduce
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking

object ReducingAndAccumulatingOperators {
    /**
     * Produces a cumulative result for each emission.
     *
     * Accumulates values starting with an initial value.
     * The result is emitted on each new value.
     * The first value is the initial value.
     * The last value is the result of the accumulation.
     * The intermediate values are the results of the accumulation up to that point.
     * The initial value is not emitted.
     * The initial value is not included in the accumulation.
     */
    object ScanExample {
        @JvmStatic
        fun main(args: Array<String>) = runBlocking {
            val list = flowOf(1, 2, 3).scan(emptyList<Int>()) { acc, value -> acc + value }.toList()
            println(list)
        }
    }

    /**
     * Accumulates values starting with an initial value.
     * The result is emitted on each new value.
     * The first value is the initial value.
     * The last value is the result of the accumulation.
     * The intermediate values are the results of the accumulation up to that point.
     * The initial value is not emitted.
     * The initial value is not included in the accumulation.
     */
    object RunningFoldExample {
        @JvmStatic
        fun main(args: Array<String>) = runBlocking {
            val result = flowOf(1, 2, 3).runningFold(emptyList<Int>()) { acc, value -> acc + value }.toList()
            println(result)
        }
    }

    /**
     * Accumulates values starting with the first value.
     * The result is emitted on each new value.
     * The first value is the first value.
     * The last value is the result of the accumulation.
     * The intermediate values are the results of the accumulation up to that point.
     * The first value is not emitted.
     */
    object RunningReduceExample {
        @JvmStatic
        fun main(args: Array<String>) = runBlocking {
            val result = flowOf(1, 2, 3).runningReduce { acc, value -> acc + value }
            result.collect {
                println(it)
            }
        }
    }
}

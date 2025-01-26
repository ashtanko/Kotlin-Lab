/*
 * Copyright 2023 Oleksii Shtanko
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

package dev.shtanko.algorithms.leetcode

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class TotalCostTest<out T : TotalCost>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(17, 12, 10, 2, 7, 2, 11, 20, 8),
                3,
                4,
                11L,
            ),
            Arguments.of(
                intArrayOf(1, 2, 4, 1),
                3,
                3,
                4L,
            ),
            Arguments.of(
                intArrayOf(1, 8, 5, 1, 2, 5, 4, 1, 7, 6),
                3,
                4,
                3L,
            ),
            Arguments.of(
                intArrayOf(1, 3, 1, 2, 5, 1, 2, 4, 1, 1, 1),
                4,
                3,
                4L,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `total cost test`(costs: IntArray, k: Int, candidates: Int, expected: Long) {
        val actual = strategy.invoke(costs, k, candidates)
        assertThat(actual).isEqualTo(expected)
    }
}

class TotalCostPriorityQueuesTest : TotalCostTest<TotalCost>(TotalCostPriorityQueues())
class TotalCostPriorityQueueTest : TotalCostTest<TotalCost>(TotalCostPriorityQueue())

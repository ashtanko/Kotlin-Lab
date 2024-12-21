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

abstract class MaxProbabilityTest<out T : MaxProbability>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                3,
                arrayOf(intArrayOf(0, 1), intArrayOf(1, 2), intArrayOf(0, 2)),
                doubleArrayOf(0.5, 0.5, 0.2),
                0,
                2,
                0.25000,
            ),
            Arguments.of(
                3,
                arrayOf(intArrayOf(0, 1), intArrayOf(1, 2), intArrayOf(0, 2)),
                doubleArrayOf(0.5, 0.5, 0.3),
                0,
                2,
                0.30000,
            ),
            Arguments.of(
                3,
                arrayOf(intArrayOf(0, 1)),
                doubleArrayOf(0.5),
                0,
                2,
                0.00000,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `max probability test`(
        num: Int,
        edges: Array<IntArray>,
        succProb: DoubleArray,
        start: Int,
        end: Int,
        expected: Double,
    ) {
        val actual = strategy.invoke(num, edges, succProb, start, end)
        assertThat(actual).isEqualTo(expected)
    }
}

class MaxProbabilityBellmanFordTest : MaxProbabilityTest<MaxProbability>(MaxProbabilityBellmanFord())
class MaxProbabilityShortestPathTest : MaxProbabilityTest<MaxProbability>(MaxProbabilityShortestPath())
class MaxProbabilityDijkstraTest : MaxProbabilityTest<MaxProbability>(MaxProbabilityDijkstra())

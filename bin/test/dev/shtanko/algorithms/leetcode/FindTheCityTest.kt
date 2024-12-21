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

package dev.shtanko.algorithms.leetcode

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class FindTheCityTest<out T : FindTheCity>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                4,
                arrayOf(
                    intArrayOf(0, 1, 3),
                    intArrayOf(1, 2, 1),
                    intArrayOf(1, 3, 4),
                    intArrayOf(2, 3, 1),
                ),
                4,
                3,
            ),
            Arguments.of(
                5,
                arrayOf(
                    intArrayOf(0, 1, 2),
                    intArrayOf(0, 4, 8),
                    intArrayOf(1, 2, 3),
                    intArrayOf(1, 4, 2),
                    intArrayOf(2, 3, 1),
                    intArrayOf(3, 4, 1),
                ),
                2,
                0,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `find the city test`(num: Int, edges: Array<IntArray>, distanceThreshold: Int, expected: Int) {
        val actual = strategy(num, edges, distanceThreshold)
        assertThat(actual).isEqualTo(expected)
    }
}

class FindTheCityDijkstraTest : FindTheCityTest<FindTheCity>(FindTheCityDijkstra())
class FindTheCityBellmanFordTest : FindTheCityTest<FindTheCity>(FindTheCityBellmanFord())
class FindTheCitySPFATest : FindTheCityTest<FindTheCity>(FindTheCitySPFA())
class FindTheCityFloydWarshallTest : FindTheCityTest<FindTheCity>(FindTheCityFloydWarshall())

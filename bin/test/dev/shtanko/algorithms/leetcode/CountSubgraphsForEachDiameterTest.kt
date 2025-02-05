/*
 * Copyright 2022 Oleksii Shtanko
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

abstract class CountSubgraphsForEachDiameterTest<out T : CountSubgraphsForEachDiameter>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                4,
                arrayOf(
                    intArrayOf(1, 2),
                    intArrayOf(2, 3),
                    intArrayOf(2, 4),
                ),
                intArrayOf(3, 4, 0),
            ),
            Arguments.of(
                2,
                arrayOf(
                    intArrayOf(1, 2),
                ),
                intArrayOf(1),
            ),
            Arguments.of(
                3,
                arrayOf(
                    intArrayOf(1, 2),
                    intArrayOf(2, 3),
                ),
                intArrayOf(2, 1),
            ),
            Arguments.of(
                5,
                arrayOf(
                    intArrayOf(1, 2),
                    intArrayOf(2, 3),
                    intArrayOf(3, 4),
                    intArrayOf(4, 5),
                ),
                intArrayOf(4, 3, 2, 1),
            ),
            Arguments.of(
                6,
                arrayOf(
                    intArrayOf(1, 2),
                    intArrayOf(2, 3),
                    intArrayOf(3, 4),
                    intArrayOf(4, 5),
                    intArrayOf(5, 6),
                ),
                intArrayOf(5, 4, 3, 2, 1),
            ),
            Arguments.of(
                7,
                arrayOf(
                    intArrayOf(1, 2),
                    intArrayOf(2, 3),
                    intArrayOf(3, 4),
                    intArrayOf(4, 5),
                    intArrayOf(5, 6),
                    intArrayOf(6, 7),
                ),
                intArrayOf(6, 5, 4, 3, 2, 1),
            ),
            Arguments.of(
                8,
                arrayOf(
                    intArrayOf(1, 2),
                    intArrayOf(2, 3),
                    intArrayOf(3, 4),
                    intArrayOf(4, 5),
                    intArrayOf(5, 6),
                    intArrayOf(6, 7),
                    intArrayOf(7, 8),
                ),
                intArrayOf(7, 6, 5, 4, 3, 2, 1),
            ),
            Arguments.of(
                9,
                arrayOf(
                    intArrayOf(1, 2),
                    intArrayOf(2, 3),
                    intArrayOf(3, 4),
                    intArrayOf(4, 5),
                    intArrayOf(5, 6),
                    intArrayOf(6, 7),
                    intArrayOf(7, 8),
                    intArrayOf(8, 9),
                ),
                intArrayOf(8, 7, 6, 5, 4, 3, 2, 1),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `count subgraphs for each diameter test`(num: Int, edges: Array<IntArray>, expected: IntArray) {
        val actual = strategy.invoke(num, edges)
        assertThat(actual).isEqualTo(expected)
    }
}

class CountSubgraphsForEachDiameterDFSTest :
    CountSubgraphsForEachDiameterTest<CountSubgraphsForEachDiameter>(CountSubgraphsForEachDiameterDFS())

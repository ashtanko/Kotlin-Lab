/*
 * Copyright 2020 Oleksii Shtanko
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

class SumOfDistancesInTreeTest {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            //  0
            //  / \
            // 1   2
            //    /|\
            //   3 4 5
            Arguments.of(
                6,
                arrayOf(
                    intArrayOf(0, 1),
                    intArrayOf(0, 2),
                    intArrayOf(2, 3),
                    intArrayOf(2, 4),
                    intArrayOf(2, 5),
                ),
                intArrayOf(8, 12, 6, 10, 10, 10),
            ),
            Arguments.of(
                1,
                arrayOf<IntArray>(),
                intArrayOf(0),
            ),
            Arguments.of(
                2,
                arrayOf(
                    intArrayOf(1, 0),
                ),
                intArrayOf(1, 1),
            ),
            Arguments.of(
                3,
                arrayOf(
                    intArrayOf(2, 0),
                    intArrayOf(1, 0),
                ),
                intArrayOf(2, 3, 3),
            ),
            Arguments.of(
                4,
                arrayOf(
                    intArrayOf(1, 2),
                    intArrayOf(2, 0),
                    intArrayOf(0, 3),
                ),
                intArrayOf(4, 6, 4, 6),
            ),
            Arguments.of(
                5,
                arrayOf(
                    intArrayOf(0, 1),
                    intArrayOf(0, 2),
                    intArrayOf(1, 3),
                    intArrayOf(0, 4),
                ),
                intArrayOf(5, 6, 8, 9, 8),
            ),
            Arguments.of(
                5,
                arrayOf(
                    intArrayOf(0, 1),
                    intArrayOf(0, 2),
                    intArrayOf(1, 3),
                    intArrayOf(1, 4),
                ),
                intArrayOf(6, 5, 9, 8, 8),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `sum of distances in tree test`(num: Int, edges: Array<IntArray>, expected: IntArray) {
        val actual = SumOfDistancesInTree().invoke(num, edges)
        assertThat(actual).isEqualTo(expected)
    }
}

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
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class SpiralMatrixTest<out T : SpiralOrder>(private val strategy: T) {

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `spiral order test`(matrix: Array<IntArray>, expected: List<Int>) {
        val actual = strategy(matrix)
        assertEquals(expected, actual)
    }

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 2, 3),
                    intArrayOf(4, 5, 6),
                    intArrayOf(7, 8, 9),
                ),
                listOf(1, 2, 3, 6, 9, 8, 7, 4, 5),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 2, 3, 4),
                    intArrayOf(5, 6, 7, 8),
                    intArrayOf(9, 10, 11, 12),
                ),
                listOf(1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 2, 3, 4),
                    intArrayOf(5, 6, 7, 8),
                    intArrayOf(9, 10, 11, 12),
                    intArrayOf(13, 14, 15, 16),
                ),
                listOf(1, 2, 3, 4, 8, 12, 16, 15, 14, 13, 9, 5, 6, 7, 11, 10),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 2, 3, 4),
                    intArrayOf(5, 6, 7, 8),
                    intArrayOf(9, 10, 11, 12),
                    intArrayOf(13, 14, 15, 16),
                    intArrayOf(17, 18, 19, 20),
                ),
                listOf(1, 2, 3, 4, 8, 12, 16, 20, 19, 18, 17, 13, 9, 5, 6, 7, 11, 15, 14, 10),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 2, 3, 4),
                    intArrayOf(5, 6, 7, 8),
                    intArrayOf(9, 10, 11, 12),
                    intArrayOf(13, 14, 15, 16),
                    intArrayOf(17, 18, 19, 20),
                    intArrayOf(21, 22, 23, 24),
                ),
                listOf(1, 2, 3, 4, 8, 12, 16, 20, 24, 23, 22, 21, 17, 13, 9, 5, 6, 7, 11, 15, 19, 18, 14, 10),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 2, 3, 4),
                    intArrayOf(5, 6, 7, 8),
                    intArrayOf(9, 10, 11, 12),
                    intArrayOf(13, 14, 15, 16),
                    intArrayOf(17, 18, 19, 20),
                    intArrayOf(21, 22, 23, 24),
                    intArrayOf(25, 26, 27, 28),
                ),
                listOf(
                    1,
                    2,
                    3,
                    4,
                    8,
                    12,
                    16,
                    20,
                    24,
                    28,
                    27,
                    26,
                    25,
                    21,
                    17,
                    13,
                    9,
                    5,
                    6,
                    7,
                    11,
                    15,
                    19,
                    23,
                    22,
                    18,
                    14,
                    10,
                ),
            ),
        )
    }
}

class SpiralOrderSolutionTest : SpiralMatrixTest<SpiralOrder>(SpiralOrderSolution())

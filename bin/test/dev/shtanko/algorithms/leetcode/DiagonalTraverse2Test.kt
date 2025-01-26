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
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class DiagonalTraverse2Test<out T : DiagonalTraverse2>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                listOf(
                    listOf(1, 2, 3),
                    listOf(4, 5, 6),
                    listOf(7, 8, 9),
                ),
                intArrayOf(1, 4, 2, 7, 5, 3, 8, 6, 9),
            ),
            Arguments.of(
                listOf(
                    listOf(1, 2, 3, 4, 5),
                    listOf(6, 7),
                    listOf(8),
                    listOf(9, 10, 11),
                    listOf(12, 13, 14, 15, 16),
                ),
                intArrayOf(1, 6, 2, 8, 7, 3, 9, 4, 12, 10, 5, 13, 11, 14, 15, 16),
            ),
            Arguments.of(
                listOf(
                    listOf(1, 2, 3),
                    listOf(4),
                    listOf(5, 6, 7),
                    listOf(8),
                    listOf(9, 10, 11),
                ),
                intArrayOf(1, 4, 2, 5, 3, 8, 6, 9, 7, 10, 11),
            ),
            Arguments.of(
                listOf(
                    listOf(1, 2, 3, 4, 5, 6),
                    listOf(7, 8, 9, 10, 11, 12),
                    listOf(13, 14, 15, 16, 17, 18),
                ),
                intArrayOf(1, 7, 2, 13, 8, 3, 14, 9, 4, 15, 10, 5, 16, 11, 6, 17, 12, 18),
            ),
            Arguments.of(
                listOf(
                    listOf(1, 2, 3, 4, 5, 6, 7),
                    listOf(8, 9, 10, 11, 12, 13, 14),
                    listOf(15, 16, 17, 18, 19, 20, 21),
                    listOf(22, 23, 24, 25, 26, 27, 28),
                ),
                intArrayOf(
                    1,
                    8,
                    2,
                    15,
                    9,
                    3,
                    22,
                    16,
                    10,
                    4,
                    23,
                    17,
                    11,
                    5,
                    24,
                    18,
                    12,
                    6,
                    25,
                    19,
                    13,
                    7,
                    26,
                    20,
                    14,
                    27,
                    21,
                    28,
                ),
            ),
            Arguments.of(
                listOf(
                    listOf(1, 2, 3, 4, 5, 6),
                    listOf(7, 8, 9, 10, 11, 12),
                    listOf(13, 14, 15, 16, 17, 18),
                    listOf(19, 20, 21, 22, 23, 24),
                    listOf(25, 26, 27, 28, 29, 30),
                ),
                intArrayOf(
                    1,
                    7,
                    2,
                    13,
                    8,
                    3,
                    19,
                    14,
                    9,
                    4,
                    25,
                    20,
                    15,
                    10,
                    5,
                    26,
                    21,
                    16,
                    11,
                    6,
                    27,
                    22,
                    17,
                    12,
                    28,
                    23,
                    18,
                    29,
                    24,
                    30,
                ),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `find diagonal order test`(nums: List<List<Int>>, expected: IntArray) {
        val actual = strategy(nums)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class DiagonalTraverse2GroupTest : DiagonalTraverse2Test<DiagonalTraverse2>(DiagonalTraverse2Group())
class DiagonalTraverse2BFSTest : DiagonalTraverse2Test<DiagonalTraverse2>(DiagonalTraverse2BFS())

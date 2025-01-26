/*
 * Copyright 2021 Oleksii Shtanko
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

class NumMatrixTest {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                CachingRows(
                    arrayOf(
                        intArrayOf(3, 0, 1, 4, 2),
                        intArrayOf(5, 6, 3, 2, 1),
                        intArrayOf(1, 2, 0, 1, 5),
                        intArrayOf(4, 1, 0, 1, 7),
                        intArrayOf(1, 0, 3, 0, 5),
                    ),
                ),
                arrayOf(
                    intArrayOf(2, 1, 4, 3),
                    intArrayOf(1, 1, 2, 2),
                    intArrayOf(1, 2, 2, 4),
                ),
                listOf(8, 11, 12),
            ),
            Arguments.of(
                CachingSmarter(
                    arrayOf(
                        intArrayOf(3, 0, 1, 4, 2),
                        intArrayOf(5, 6, 3, 2, 1),
                        intArrayOf(1, 2, 0, 1, 5),
                        intArrayOf(4, 1, 0, 1, 7),
                        intArrayOf(1, 0, 3, 0, 5),
                    ),
                ),
                arrayOf(
                    intArrayOf(2, 1, 4, 3),
                    intArrayOf(1, 1, 2, 2),
                    intArrayOf(1, 2, 2, 4),
                ),
                listOf(8, 11, 12),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `max product test`(param: NumMatrix, matrix: Array<IntArray>, expected: List<Int>) {
        val actual: MutableList<Int> = ArrayList()
        for (m in matrix) {
            val (row1, col1, row2, col2) = m
            actual.add(param.sumRegion(row1, col1, row2, col2))
        }
        assertThat(actual).isEqualTo(expected)
    }
}

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
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class TheKWeakestRowsInMatrixTest<out T : KWeakestRows>(private val solution: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 1, 0, 0, 0),
                    intArrayOf(1, 1, 1, 1, 0),
                    intArrayOf(1, 0, 0, 0, 0),
                    intArrayOf(1, 1, 0, 0, 0),
                    intArrayOf(1, 1, 1, 1, 1),
                ),
                3,
                intArrayOf(2, 0, 3),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 0, 0, 0),
                    intArrayOf(1, 1, 1, 1),
                    intArrayOf(1, 0, 0, 0),
                    intArrayOf(1, 0, 0, 0),
                ),
                2,
                intArrayOf(0, 2),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 1, 1, 1, 1, 1),
                    intArrayOf(1, 1, 1, 1, 1, 1),
                    intArrayOf(1, 1, 1, 1, 1, 1),
                ),
                1,
                intArrayOf(0),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 0),
                    intArrayOf(1, 0),
                    intArrayOf(1, 0),
                    intArrayOf(1, 1),
                ),
                4,
                intArrayOf(0, 1, 2, 3),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 1, 1, 1, 1, 1),
                    intArrayOf(1, 1, 1, 1, 1, 1),
                    intArrayOf(1, 1, 1, 1, 1, 1),
                ),
                3,
                intArrayOf(0, 1, 2),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `k weakest rows test`(matrix: Array<IntArray>, k: Int, expected: IntArray) {
        val actual = solution(matrix, k)
        assertArrayEquals(expected, actual)
    }
}

class KWeakestRowsPQTest : TheKWeakestRowsInMatrixTest<KWeakestRows>(KWeakestRowsPQ())
class KWeakestRowsBFTest : TheKWeakestRowsInMatrixTest<KWeakestRows>(KWeakestRowsBF())

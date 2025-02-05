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

abstract class MatrixRankTransformTest<out T : MatrixRankTransform>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 2),
                    intArrayOf(3, 4),
                ),
                arrayOf(
                    intArrayOf(1, 2),
                    intArrayOf(2, 3),
                ),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(7, 7),
                    intArrayOf(7, 7),
                ),
                arrayOf(
                    intArrayOf(1, 1),
                    intArrayOf(1, 1),
                ),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(20, -21, 14),
                    intArrayOf(-19, 4, 19),
                    intArrayOf(22, -47, 24),
                    intArrayOf(-19, 4, 19),
                ),
                arrayOf(
                    intArrayOf(4, 2, 3),
                    intArrayOf(1, 3, 4),
                    intArrayOf(5, 1, 6),
                    intArrayOf(1, 3, 4),
                ),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `matrix rank transform test`(matrix: Array<IntArray>, expected: Array<IntArray>) {
        val actual = strategy.invoke(matrix)
        assertThat(actual).isEqualTo(expected)
    }
}

class MatrixRankTransformMapTest : MatrixRankTransformTest<MatrixRankTransform>(MatrixRankTransformMap())

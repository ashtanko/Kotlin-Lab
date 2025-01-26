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

abstract class SpiralMatrix2Test<out T : SpiralMatrix2>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                0,
                emptyArray<IntArray>(),
            ),
            Arguments.of(
                1,
                arrayOf(intArrayOf(1)),
            ),
            Arguments.of(
                2,
                arrayOf(
                    intArrayOf(1, 2),
                    intArrayOf(4, 3),
                ),
            ),
            Arguments.of(
                3,
                arrayOf(
                    intArrayOf(1, 2, 3),
                    intArrayOf(8, 9, 4),
                    intArrayOf(7, 6, 5),
                ),
            ),
            Arguments.of(
                4,
                arrayOf(
                    intArrayOf(1, 2, 3, 4),
                    intArrayOf(12, 13, 14, 5),
                    intArrayOf(11, 16, 15, 6),
                    intArrayOf(10, 9, 8, 7),
                ),
            ),
            Arguments.of(
                5,
                arrayOf(
                    intArrayOf(1, 2, 3, 4, 5),
                    intArrayOf(16, 17, 18, 19, 6),
                    intArrayOf(15, 24, 25, 20, 7),
                    intArrayOf(14, 23, 22, 21, 8),
                    intArrayOf(13, 12, 11, 10, 9),
                ),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `generate matrix test`(num: Int, expected: Array<IntArray>) {
        val actual = strategy.generateMatrix(num)
        assertThat(actual).isEqualTo(expected)
    }
}

class SpiralMatrix2TraverseTest : SpiralMatrix2Test<SpiralMatrix2>(SpiralMatrix2Traverse())
class SpiralMatrix2OptimizedTest : SpiralMatrix2Test<SpiralMatrix2>(SpiralMatrix2Optimized())

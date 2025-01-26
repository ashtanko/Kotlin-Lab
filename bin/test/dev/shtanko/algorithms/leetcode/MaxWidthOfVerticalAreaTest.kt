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

abstract class MaxWidthOfVerticalAreaTest<out T : MaxWidthOfVerticalArea>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(
                    intArrayOf(8, 7),
                    intArrayOf(9, 9),
                    intArrayOf(7, 4),
                    intArrayOf(9, 7),
                ),
                1,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(3, 1),
                    intArrayOf(9, 0),
                    intArrayOf(1, 0),
                    intArrayOf(1, 4),
                    intArrayOf(5, 3),
                    intArrayOf(8, 8),
                ),
                3,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `max width of vertical area test`(points: Array<IntArray>, expected: Int) {
        val actual = strategy(points)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class MaxWidthOfVerticalAreaSortTest : MaxWidthOfVerticalAreaTest<MaxWidthOfVerticalArea>(MaxWidthOfVerticalAreaSort())

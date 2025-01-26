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
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class FindPolygonWithLargestPerimeterTest<out T : FindPolygonWithLargestPerimeter>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(5, 5, 5),
                15L,
            ),
            Arguments.of(
                intArrayOf(1, 12, 1, 2, 5, 50, 3),
                12L,
            ),
            Arguments.of(
                intArrayOf(5, 5, 50),
                -1L,
            ),
            Arguments.of(
                intArrayOf(),
                -1L,
            ),
            Arguments.of(
                intArrayOf(1),
                -1L,
            ),
            Arguments.of(
                intArrayOf(1, 2, 1),
                -1L,
            ),
            Arguments.of(
                intArrayOf(1, 2, 1, 2, 1),
                7L,
            ),
            Arguments.of(
                intArrayOf(1, 2, 1, 2, 1, 2, 1),
                10L,
            ),
            Arguments.of(
                intArrayOf(1, 2, 1, 2, 1, 2, 1, 2, 1),
                13L,
            ),
            Arguments.of(
                intArrayOf(1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1),
                16L,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `largest perimeter test`(nums: IntArray, expected: Long) {
        val actual = strategy(nums)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class FindPolygonWithLargestPerimeterSortTest :
    FindPolygonWithLargestPerimeterTest<FindPolygonWithLargestPerimeter>(FindPolygonWithLargestPerimeterSort())

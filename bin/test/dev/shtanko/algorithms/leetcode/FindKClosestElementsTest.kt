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

class FindKClosestElementsTest {

    class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5),
                4,
                3,
                listOf(1, 2, 3, 4),
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5),
                4,
                -1,
                listOf(1, 2, 3, 4),
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5),
                4,
                6,
                listOf(2, 3, 4, 5),
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5),
                4,
                4,
                listOf(2, 3, 4, 5),
            ),
            Arguments.of(
                intArrayOf(),
                0,
                0,
                listOf<Int>(),
            ),
            Arguments.of(
                intArrayOf(1),
                1,
                1,
                listOf(1),
            ),
            Arguments.of(
                intArrayOf(1, 2),
                1,
                1,
                listOf(1),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `sort with custom comparator`(arr: IntArray, k: Int, x: Int, expected: List<Int>) {
        assertFindClosestElements(FindKClosestElements::sortWithCustomComparator, arr, k, x, expected)
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `binary search and sliding window`(arr: IntArray, k: Int, x: Int, expected: List<Int>) {
        assertFindClosestElements(FindKClosestElements::bsSlidingWindow, arr, k, x, expected)
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `binary search find the left bound`(arr: IntArray, k: Int, x: Int, expected: List<Int>) {
        assertFindClosestElements(FindKClosestElements::bsFindTheLeftBound, arr, k, x, expected)
    }

    private fun assertFindClosestElements(
        strategy: (arr: IntArray, k: Int, x: Int) -> List<Int>,
        arr: IntArray,
        k: Int,
        x: Int,
        expected: List<Int>,
    ) {
        val actual = strategy.invoke(arr, k, x)
        assertThat(actual).containsAll(expected)
    }
}

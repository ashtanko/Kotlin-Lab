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
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class SortJumbledNumbersTest<out T : SortJumbledNumbers>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(8, 9, 4, 0, 2, 1, 3, 5, 7, 6),
                intArrayOf(991, 338, 38),
                intArrayOf(338, 38, 991),
            ),
            Arguments.of(
                intArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9),
                intArrayOf(789, 456, 123),
                intArrayOf(123, 456, 789),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun sortJumbledTest(mapping: IntArray, nums: IntArray, expected: IntArray) {
        val actual = strategy(mapping, nums)
        assertThat(actual).isEqualTo(expected)
    }
}

class SortJumbledNumbersSortingTest : SortJumbledNumbersTest<SortJumbledNumbers>(SortJumbledNumbersSorting())
class SortJumbledNumbersNoSortTest : SortJumbledNumbersTest<SortJumbledNumbers>(SortJumbledNumbersNoSort())

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

abstract class SortItemsTest<out T : SortItems>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                8,
                2,
                intArrayOf(-1, -1, 1, 0, 0, 1, 0, -1),
                listOf(listOf(), listOf(6), listOf(5), listOf(6), listOf(3, 6), listOf(), listOf(), listOf()),
                intArrayOf(6, 3, 4, 1, 5, 2, 0, 7),
            ),
            Arguments.of(
                8,
                2,
                intArrayOf(-1, -1, 1, 0, 0, 1, 0, -1),
                listOf(listOf(), listOf(6), listOf(5), listOf(6), listOf(3, 6), listOf(), listOf(4), listOf()),
                intArrayOf(),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `sort items test`(num: Int, m: Int, group: IntArray, beforeItems: List<List<Int>>, expected: IntArray) {
        val actual = strategy(num, m, group, beforeItems)
        Assertions.assertThat(actual).containsExactlyInAnyOrder(*expected)
    }
}

class TopologicalSortingTest : SortItemsTest<SortItems>(TopologicalSorting())

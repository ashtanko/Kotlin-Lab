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

abstract class SortThePeopleTest<out T : SortThePeople>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf("Mary", "John", "Emma"),
                intArrayOf(180, 165, 170),
                arrayOf("Mary", "Emma", "John"),
            ),
            Arguments.of(
                arrayOf("Alice", "Bob", "Bob"),
                intArrayOf(155, 185, 150),
                arrayOf("Bob", "Alice", "Bob"),
            ),
            Arguments.of(
                arrayOf("Alice"),
                intArrayOf(155),
                arrayOf("Alice"),
            ),
            Arguments.of(
                arrayOf<String>(),
                intArrayOf(),
                arrayOf<String>(),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun sortPeopleTest(names: Array<String>, heights: IntArray, expected: Array<String>) {
        val actual = strategy(names, heights)
        assertThat(actual).isEqualTo(expected)
    }
}

class SortThePeopleZipTest : SortThePeopleTest<SortThePeople>(SortThePeopleZip())
class SortThePeopleMapTest : SortThePeopleTest<SortThePeople>(SortThePeopleMap())
class SortThePeopleTreeMapTest : SortThePeopleTest<SortThePeople>(SortThePeopleTreeMap())
class SortThePeopleSortPermutationTest : SortThePeopleTest<SortThePeople>(SortThePeopleSortPermutation())
class SortThePeopleQuickSortTest : SortThePeopleTest<SortThePeople>(SortThePeopleQuickSort())
class SortThePeopleMergeSortTest : SortThePeopleTest<SortThePeople>(SortThePeopleMergeSort())

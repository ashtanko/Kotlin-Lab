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

abstract class RelativeSortArrayTest<out T : RelativeSortArray>(private val strategy: T) {

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `relative sort array test`(arr1: IntArray, arr2: IntArray, expected: IntArray) {
        val actual = strategy(arr1, arr2)
        assertArrayEquals(expected, actual)
    }

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19),
                intArrayOf(2, 1, 4, 3, 9, 6),
                intArrayOf(2, 2, 2, 1, 4, 3, 3, 9, 6, 7, 19),
            ),
            Arguments.of(
                intArrayOf(2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19),
                intArrayOf(2, 1, 4, 3, 9, 6),
                intArrayOf(2, 2, 2, 1, 4, 3, 3, 9, 6, 7, 19),
            ),
        )
    }
}

class RelativeSortArrayTwoLoopsTest : RelativeSortArrayTest<RelativeSortArray>(RelativeSortArrayTwoLoops())
class RelativeSortArrayHashMapTest : RelativeSortArrayTest<RelativeSortArray>(RelativeSortArrayHashMap())
class RelativeSortArrayCountingSortTest : RelativeSortArrayTest<RelativeSortArray>(RelativeSortArrayCountingSort())
class RelativeSortArrayArraySortTest : RelativeSortArrayTest<RelativeSortArray>(RelativeSortArrayArray())
class RelativeSortArrayTreeMapTest : RelativeSortArrayTest<RelativeSortArray>(RelativeSortArrayTreeMap())

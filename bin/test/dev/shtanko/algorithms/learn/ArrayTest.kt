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

package dev.shtanko.algorithms.learn

import java.util.stream.Stream
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

internal class ArrayTest {

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsSortsProvider::class)
    internal fun `find min using sort test`(arr: IntArray, expected: Int) {
        val actual = secondMinSort(arr)
        assertEquals(expected, actual)
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsSortsProvider::class)
    internal fun `find min second straight forward test`(arr: IntArray, expected: Int) {
        val actual = secondStraightForward(arr)
        assertEquals(expected, actual)
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsUniqueProvider::class)
    internal fun `find first unique elements test`(arr: IntArray, expected: IntArray) {
        val actual = uniqueWholeNumbersSet(arr)
        assertArrayEquals(expected, actual)
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsMergeProvider::class)
    internal fun `merge two sorted arrays using plus operator test`(
        arr1: IntArray,
        arr2: IntArray,
        expected: IntArray,
    ) {
        val actual = mergeTwoSortedPlus(arr1, arr2)
        assertArrayEquals(expected, actual)
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsMergeProvider::class)
    internal fun `merge two sorted arrays straight forward test`(arr1: IntArray, arr2: IntArray, expected: IntArray) {
        val actual = mergeTwoSortedSF(arr1, arr2)
        assertArrayEquals(expected, actual)
    }

    private class InputArgumentsUniqueProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(1, 1, 2, 2, 2, 3, 3, 4, 5, 5),
                intArrayOf(1, 2, 3, 4, 5),
            ),
            Arguments.of(
                intArrayOf(),
                intArrayOf(),
            ),
        )
    }

    private class InputArgumentsMergeProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5, 6),
                intArrayOf(7, 8, 9),
                intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9),
            ),
            Arguments.of(
                intArrayOf(),
                intArrayOf(),
                intArrayOf(),
            ),
        )
    }

    private class InputArgumentsSortsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(1, 3, 2, 4, 5),
                2,
            ),
            Arguments.of(
                intArrayOf(12, 25, 8, 55, 10, 33, 17, 11),
                10,
            ),
            Arguments.of(
                intArrayOf(4, 8, 15, 16, 23, 42),
                8,
            ),
            Arguments.of(
                intArrayOf(),
                -1,
            ),
        )
    }
}

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
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class IntersectionThreeSortedArraysTest<out T : IntersectionThreeSortedArrays>(private val strategy: T) {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5),
                intArrayOf(1, 2, 5, 7, 9),
                intArrayOf(1, 3, 4, 5, 8),
                listOf(1, 5),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `intersection of three sorted arrays test`(
        arr1: IntArray,
        arr2: IntArray,
        arr3: IntArray,
        expected: List<Int>,
    ) {
        val actual = strategy.invoke(arr1, arr2, arr3)
        assertEquals(expected, actual)
    }
}

class IntersectionThreeSortedBruteForceTest :
    IntersectionThreeSortedArraysTest<IntersectionThreeSortedBruteForce>(IntersectionThreeSortedBruteForce())

class IntersectionThreeSortedThreePointersTest :
    IntersectionThreeSortedArraysTest<IntersectionThreeSortedThreePointers>(IntersectionThreeSortedThreePointers())

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

abstract class PeakIndexInMountainArrayTest<out T : PeakIndexInMountainArrayStrategy>(private val strategy: T) {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(0, 1, 0),
                1,
            ),
            Arguments.of(
                intArrayOf(0, 2, 1, 0),
                1,
            ),
            Arguments.of(
                intArrayOf(0, 10, 5, 2),
                1,
            ),
            Arguments.of(
                intArrayOf(3, 4, 5, 1),
                2,
            ),
            Arguments.of(
                intArrayOf(24, 69, 100, 99, 79, 78, 67, 36, 26, 19),
                2,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `peak index in mountain test`(arr: IntArray, expected: Int) {
        val actual = strategy.invoke(arr)
        assertEquals(expected, actual)
    }
}

class PeakIndexInMountainArrayLinearScanTest :
    PeakIndexInMountainArrayTest<PeakIndexInMountainArrayLinearScan>(PeakIndexInMountainArrayLinearScan())

class PeakIndexInMountainArrayBinarySearchTest :
    PeakIndexInMountainArrayTest<PeakIndexInMountainArrayBinarySearch>(PeakIndexInMountainArrayBinarySearch())

class PeakIndexInMountainArrayBetterThanBinarySearchTest :
    PeakIndexInMountainArrayTest<PeakIndexInMountainArrayBetterThanBinarySearch>(
        PeakIndexInMountainArrayBetterThanBinarySearch(),
    )

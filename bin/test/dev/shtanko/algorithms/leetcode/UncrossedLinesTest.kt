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

abstract class UncrossedLinesTest<out T : UncrossedLines>(private val strategy: T) {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(1, 4, 2),
                intArrayOf(1, 2, 4),
                2,
            ),
            Arguments.of(
                intArrayOf(2, 5, 1, 2, 5),
                intArrayOf(10, 5, 2, 1, 5, 2),
                3,
            ),
            Arguments.of(
                intArrayOf(1, 3, 7, 1, 7, 5),
                intArrayOf(1, 9, 2, 5, 1),
                2,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5),
                intArrayOf(6, 7, 8, 9, 10),
                0,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5, 6),
                intArrayOf(1, 2, 3, 4, 5, 6),
                6,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5, 6),
                intArrayOf(6, 5, 4, 3, 2, 1),
                1,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `max uncrossed lines test`(nums1: IntArray, nums2: IntArray, expected: Int) {
        val actual = strategy.maxUncrossedLines(nums1, nums2)
        assertEquals(expected, actual)
    }
}

class UncrossedLinesRecursiveDPTest : UncrossedLinesTest<UncrossedLines>(UncrossedLinesRecursiveDP())
class UncrossedLinesIterativeDPTest : UncrossedLinesTest<UncrossedLines>(UncrossedLinesIterativeDP())

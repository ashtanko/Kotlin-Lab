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

abstract class FindUnsortedSubArrayTest<out T : FindUnsortedSubArray>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(),
                0,
            ),
            Arguments.of(
                intArrayOf(2, 6, 4, 8, 10, 9, 15),
                5,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4),
                0,
            ),
            Arguments.of(
                intArrayOf(1),
                0,
            ),
            Arguments.of(
                intArrayOf(1, 3, 2, 2, 2),
                4,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 3, 3),
                0,
            ),
            Arguments.of(
                intArrayOf(1, 3, 2, 3, 3),
                2,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9),
                0,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11),
                0,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `find unsorted sub array test`(nums: IntArray, expected: Int) {
        val actual = strategy.invoke(nums)
        assertThat(actual).isEqualTo(expected)
    }
}

class FindUnsortedSubArrayBruteForceTest :
    FindUnsortedSubArrayTest<FindUnsortedSubArrayBruteForce>(FindUnsortedSubArrayBruteForce())

class FindUnsortedSubArrayBetterBruteForceTest :
    FindUnsortedSubArrayTest<FindUnsortedSubArrayBetterBruteForce>(FindUnsortedSubArrayBetterBruteForce())

class FindUnsortedSubArraySortTest :
    FindUnsortedSubArrayTest<FindUnsortedSubArraySort>(FindUnsortedSubArraySort())

class FindUnsortedSubStackTest :
    FindUnsortedSubArrayTest<FindUnsortedSubArrayStack>(FindUnsortedSubArrayStack())

class FindUnsortedSubArrayConstSpaceTest :
    FindUnsortedSubArrayTest<FindUnsortedSubArrayConstSpace>(FindUnsortedSubArrayConstSpace())

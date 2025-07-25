/*
 * Designed and developed by 2020 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
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
import org.junit.jupiter.params.support.ParameterDeclarations

abstract class TwoSumLessThanKTest<out T : TwoSumLessThanKStrategy>(private val strategy: T) {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(
            parameters: ParameterDeclarations?,
            context: ExtensionContext?,
        ): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(34, 23, 1, 24, 75, 33, 54, 8),
                60,
                58,
            ),
            Arguments.of(
                intArrayOf(10, 20, 30),
                15,
                -1,
            ),
            Arguments.of(
                intArrayOf(),
                0,
                -1,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5),
                10,
                9,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5),
                0,
                -1,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `two sum less than K test`(nums: IntArray, k: Int, expected: Int) {
        val actual = strategy.invoke(nums, k)
        assertEquals(expected, actual)
    }
}

class TwoSumLessThanKBruteForceTest :
    TwoSumLessThanKTest<TwoSumLessThanKBruteForce>(TwoSumLessThanKBruteForce())

class TwoSumLessThanKTwoPointersTest :
    TwoSumLessThanKTest<TwoSumLessThanKTwoPointers>(TwoSumLessThanKTwoPointers())

class TwoSumLessThanKBinarySearchTest :
    TwoSumLessThanKTest<TwoSumLessThanKBinarySearch>(TwoSumLessThanKBinarySearch())

class TwoSumLessThanKCountingSortTest :
    TwoSumLessThanKTest<TwoSumLessThanKCountingSort>(TwoSumLessThanKCountingSort())

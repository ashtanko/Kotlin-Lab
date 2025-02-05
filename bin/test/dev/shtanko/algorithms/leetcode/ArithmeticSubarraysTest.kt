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

abstract class ArithmeticSubarraysTest<out T : ArithmeticSubarrays>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(4, 6, 5, 9, 3, 7),
                intArrayOf(0, 0, 2),
                intArrayOf(2, 3, 5),
                listOf(true, false, true),
            ),
            Arguments.of(
                intArrayOf(-12, -9, -3, -12, -6, 15, 20, -25, -20, -15, -10),
                intArrayOf(0, 1, 6, 4, 8, 7),
                intArrayOf(4, 4, 9, 7, 9, 10),
                listOf(false, true, false, false, true, true),
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4),
                intArrayOf(1),
                intArrayOf(3),
                listOf(true),
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4),
                intArrayOf(1),
                intArrayOf(2),
                listOf(true),
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4),
                intArrayOf(1, 2),
                intArrayOf(2, 3),
                listOf(true, true),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `check arithmetic subarrays test`(nums: IntArray, l: IntArray, r: IntArray, expected: List<Boolean>) {
        val actual = strategy(nums, l, r)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class ArithmeticSubarraysSortTest : ArithmeticSubarraysTest<ArithmeticSubarrays>(ArithmeticSubarraysSort())
class ArithmeticSubarraysSetTest : ArithmeticSubarraysTest<ArithmeticSubarrays>(ArithmeticSubarraysSet())

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

abstract class LongestContinuousSubarrayTest<out T : LongestContinuousSubarray>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(),
                4,
                0,
            ),
            Arguments.of(
                intArrayOf(1),
                0,
                1,
            ),
            Arguments.of(
                intArrayOf(8, 2, 4, 7),
                4,
                2,
            ),
            Arguments.of(
                intArrayOf(10, 1, 2, 4, 7, 2),
                5,
                4,
            ),
            Arguments.of(
                intArrayOf(4, 2, 2, 2, 4, 4, 2, 2),
                0,
                3,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun longestSubarrayTest(nums: IntArray, limit: Int, expected: Int) {
        val actual = strategy(nums, limit)
        assertThat(actual).isEqualTo(expected)
    }
}

class LongestContinuousSubarrayDoubleDequeTest :
    LongestContinuousSubarrayTest<LongestContinuousSubarrayDoubleDeque>(LongestContinuousSubarrayDoubleDeque())

class LongestContinuousSubarrayMultisetTest :
    LongestContinuousSubarrayTest<LongestContinuousSubarrayMultiset>(LongestContinuousSubarrayMultiset())

class LongestContinuousSubarrayTwoHeapsTest :
    LongestContinuousSubarrayTest<LongestContinuousSubarrayTwoHeaps>(LongestContinuousSubarrayTwoHeaps())

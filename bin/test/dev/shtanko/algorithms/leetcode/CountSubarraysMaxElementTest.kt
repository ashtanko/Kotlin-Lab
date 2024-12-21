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

abstract class CountSubarraysMaxElementTest<out T : CountSubarraysMaxElement>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(1, 3, 2, 3, 3),
                2,
                6,
            ),
            Arguments.of(
                intArrayOf(1, 4, 2, 1),
                3,
                0,
            ),
            Arguments.of(
                intArrayOf(2, 1, 4, 1, 1, 2, 2, 1),
                5,
                0,
            ),
            Arguments.of(
                intArrayOf(2, 9, 2, 5, 6),
                2,
                0,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun countSubarraysTest(nums: IntArray, k: Int, expected: Long) {
        val actual = strategy(nums, k)
        assertThat(actual).isEqualTo(expected)
    }
}

class CountSubarraysMaxElementSlidingWindowTest :
    CountSubarraysMaxElementTest<CountSubarraysMaxElementSlidingWindow>(CountSubarraysMaxElementSlidingWindow())

class CountSubarraysMaxElementMaxElementTest :
    CountSubarraysMaxElementTest<CountSubarraysMaxElementMaxElement>(CountSubarraysMaxElementMaxElement())

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
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class CountSubarraysWithFixedBoundsTest<out T : CountSubarraysWithFixedBounds>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(1, 3, 5, 2, 7, 5),
                1,
                5,
                2,
            ),
            Arguments.of(
                intArrayOf(1, 1, 1, 1),
                1,
                1,
                10,
            ),
            Arguments.of(
                intArrayOf(5, 6, 7, 12, 7, 2),
                5,
                7,
                1,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5),
                3,
                3,
                1,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5),
                1,
                1,
                1,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `count subarrays test`(nums: IntArray, minK: Int, maxK: Int, expected: Long) {
        val actual = strategy.countSubarrays(nums, minK, maxK)
        assertThat(actual).isEqualTo(expected)
    }
}

class CountSubarraysWithFixedBoundsSlidingWindowTest :
    CountSubarraysWithFixedBoundsTest<CountSubarraysWithFixedBounds>(CountSubarraysWithFixedBoundsSlidingWindow())

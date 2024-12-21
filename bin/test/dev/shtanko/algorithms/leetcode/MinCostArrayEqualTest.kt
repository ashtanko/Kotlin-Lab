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

abstract class MinCostArrayEqualTest<out T : MinCostArrayEqual>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(1, 3, 5, 2),
                intArrayOf(2, 3, 1, 14),
                8,
            ),
            Arguments.of(
                intArrayOf(2, 2, 2, 2, 2),
                intArrayOf(4, 2, 8, 1, 3),
                0,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `min cost test`(nums: IntArray, cost: IntArray, expected: Long) {
        val actual = strategy.minCost(nums, cost)
        assertThat(actual).isEqualTo(expected)
    }
}

class MinCostArrayEqualPrefixSumTest : MinCostArrayEqualTest<MinCostArrayEqual>(MinCostArrayEqualPrefixSum())
class MinCostArrayEqualBinarySearchTest : MinCostArrayEqualTest<MinCostArrayEqual>(MinCostArrayEqualBinarySearch())

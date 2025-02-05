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

abstract class SplitArrayLargestSumTest<out T : SplitArrayLargestSum>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(7, 2, 5, 10, 8),
                2,
                18,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5),
                2,
                9,
            ),
            Arguments.of(
                intArrayOf(1, 2),
                2,
                2,
            ),
            Arguments.of(
                intArrayOf(1, 4, 4),
                3,
                4,
            ),
            Arguments.of(
                intArrayOf(1, 4, 4),
                2,
                5,
            ),
            Arguments.of(
                intArrayOf(),
                0,
                0,
            ),
            Arguments.of(
                intArrayOf(1),
                1,
                1,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5),
                2,
                9,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5),
                3,
                6,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `split array test`(nums: IntArray, k: Int, expected: Int) {
        val actual = strategy.invoke(nums, k)
        assertThat(actual).isEqualTo(expected)
    }
}

class SplitArrayLargestSumMaxSumTest : SplitArrayLargestSumTest<SplitArrayLargestSum>(SplitArrayLargestSumMaxSum())

class SplitArrayLargestSumGreedyTest : SplitArrayLargestSumTest<SplitArrayLargestSum>(SplitArrayLargestSumGreedy())

class SplitArrayLargestSumBinarySearchTest :
    SplitArrayLargestSumTest<SplitArrayLargestSum>(SplitArrayLargestSumBinarySearch())

class SplitArrayLargestSumDPTest : SplitArrayLargestSumTest<SplitArrayLargestSum>(SplitArrayLargestSumDP())

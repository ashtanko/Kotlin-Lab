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

abstract class SmallestRangeFinderTest<out T : SmallestRangeFinder>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                listOf(
                    listOf(4, 10, 15, 24, 26),
                    listOf(0, 9, 12, 20),
                    listOf(5, 18, 22, 30),
                ),
                intArrayOf(20, 24),
            ),
            Arguments.of(
                listOf(
                    listOf(1, 2, 3),
                    listOf(1, 2, 3),
                    listOf(1, 2, 3),
                ),
                intArrayOf(1, 1),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun smallestRangeTest(nums: List<List<Int>>, expected: IntArray) {
        val actual = strategy(nums)
        assertThat(actual).isEqualTo(expected)
    }
}

class SmallestRangeFinderTwoPointerTest :
    SmallestRangeFinderTest<SmallestRangeFinderTwoPointer>(SmallestRangeFinderTwoPointer())

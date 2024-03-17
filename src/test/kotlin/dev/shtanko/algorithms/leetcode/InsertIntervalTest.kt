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
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class InsertIntervalTest<out T : InsertInterval>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(intArrayOf(1, 3), intArrayOf(6, 9)),
                intArrayOf(2, 5),
                arrayOf(intArrayOf(1, 5), intArrayOf(6, 9)),
            ),
            Arguments.of(
                arrayOf(intArrayOf(1, 2), intArrayOf(3, 5), intArrayOf(6, 7), intArrayOf(8, 10), intArrayOf(12, 16)),
                intArrayOf(4, 8),
                arrayOf(intArrayOf(1, 2), intArrayOf(3, 10), intArrayOf(12, 16)),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `insert interval test`(intervals: Array<IntArray>, newInterval: IntArray, expected: Array<IntArray>) {
        val actual = strategy(intervals, newInterval)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class InsertIntervalLinearSearchTest : InsertIntervalTest<InsertInterval>(InsertIntervalLinearSearch())
class InsertIntervalBinarySearchTest : InsertIntervalTest<InsertInterval>(InsertIntervalBinarySearch())

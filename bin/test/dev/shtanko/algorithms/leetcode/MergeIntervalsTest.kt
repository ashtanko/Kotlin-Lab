/*
 * Copyright 2020 Oleksii Shtanko
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
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class MergeIntervalsTest<out T : MergeIntervals>(private val strategy: T) {

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `merge intervals test`(intervals: Array<IntArray>, expected: Array<IntArray>) {
        val actual = strategy.invoke(intervals)
        assertArrayEquals(expected, actual)
    }

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 3),
                    intArrayOf(2, 6),
                    intArrayOf(8, 10),
                    intArrayOf(15, 18),
                ),
                arrayOf(
                    intArrayOf(1, 6),
                    intArrayOf(8, 10),
                    intArrayOf(15, 18),
                ),
            ),
            Arguments.of(
                arrayOf<IntArray>(),
                arrayOf<IntArray>(),
            ),
        )
    }
}

class MergeIntervalsConnectedComponentsTest : MergeIntervalsTest<MergeIntervals>(MergeIntervalsConnectedComponents())

class MergeIntervalsSortingTest : MergeIntervalsTest<MergeIntervals>(MergeIntervalsSorting())

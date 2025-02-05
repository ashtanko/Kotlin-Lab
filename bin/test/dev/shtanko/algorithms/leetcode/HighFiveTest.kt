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

abstract class HighFiveTest<out T : HighFiveStrategy>(private val strategy: T) {

    class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 91),
                    intArrayOf(1, 92),
                    intArrayOf(2, 93),
                    intArrayOf(2, 97),
                    intArrayOf(1, 60),
                    intArrayOf(2, 77),
                    intArrayOf(1, 65),
                    intArrayOf(1, 87),
                    intArrayOf(1, 100),
                    intArrayOf(2, 100),
                    intArrayOf(2, 76),
                ),
                arrayOf(
                    intArrayOf(1, 87),
                    intArrayOf(2, 88),
                ),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 100),
                    intArrayOf(7, 100),
                    intArrayOf(1, 100),
                    intArrayOf(7, 100),
                    intArrayOf(1, 100),
                    intArrayOf(7, 100),
                    intArrayOf(1, 100),
                    intArrayOf(7, 100),
                    intArrayOf(1, 100),
                    intArrayOf(7, 100),
                ),
                arrayOf(
                    intArrayOf(1, 100),
                    intArrayOf(7, 100),
                ),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `high five test`(items: Array<IntArray>, expected: Array<IntArray>) {
        val actual = strategy.invoke(items)
        assertArrayEquals(expected, actual)
    }
}

class HighFivePriorityQueueTest : HighFiveTest<HighFivePriorityQueue>(HighFivePriorityQueue())
class HighFiveSortTest : HighFiveTest<HighFiveSort>(HighFiveSort())

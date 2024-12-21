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

abstract class MinGroupsTest<out T : MinGroups>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(
                    intArrayOf(5, 10),
                    intArrayOf(6, 8),
                    intArrayOf(1, 5),
                    intArrayOf(2, 3),
                    intArrayOf(1, 10),
                ),
                3,
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 3),
                    intArrayOf(5, 6),
                    intArrayOf(8, 10),
                    intArrayOf(11, 13),
                ),
                1,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun minimumGroupsTst(intervals: Array<IntArray>, expected: Int) {
        val actual = strategy(intervals)
        assertThat(actual).isEqualTo(expected)
    }
}

class MinGroupsSortTest : MinGroupsTest<MinGroupsSort>(MinGroupsSort())
class MinGroupsLineSweepTest : MinGroupsTest<MinGroupsLineSweep>(MinGroupsLineSweep())
class MinGroupsLineSweep2Test : MinGroupsTest<MinGroupsLineSweep2>(MinGroupsLineSweep2())

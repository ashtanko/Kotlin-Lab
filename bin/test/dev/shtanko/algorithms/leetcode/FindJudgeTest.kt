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
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class FindJudgeTest<out T : FindJudge>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                2,
                arrayOf<IntArray>(),
                -1,
            ),
            Arguments.of(
                2,
                arrayOf(intArrayOf(1, 2)),
                2,
            ),
            Arguments.of(
                3,
                arrayOf(intArrayOf(1, 3), intArrayOf(2, 3)),
                3,
            ),
            Arguments.of(
                3,
                arrayOf(intArrayOf(1, 3), intArrayOf(2, 3), intArrayOf(3, 1)),
                -1,
            ),
            Arguments.of(
                3,
                arrayOf(intArrayOf(1, 2), intArrayOf(2, 3)),
                -1,
            ),
            Arguments.of(
                4,
                arrayOf(intArrayOf(1, 3), intArrayOf(1, 4), intArrayOf(2, 3), intArrayOf(2, 4), intArrayOf(4, 3)),
                3,
            ),
            Arguments.of(
                5,
                arrayOf(
                    intArrayOf(1, 3),
                    intArrayOf(2, 3),
                    intArrayOf(4, 3),
                    intArrayOf(4, 1),
                    intArrayOf(5, 3),
                    intArrayOf(5, 1),
                    intArrayOf(5, 4),
                ),
                3,
            ),
            Arguments.of(
                5,
                arrayOf(
                    intArrayOf(2, 1),
                    intArrayOf(3, 1),
                    intArrayOf(4, 2),
                    intArrayOf(4, 3),
                    intArrayOf(4, 5),
                    intArrayOf(5, 1),
                ),
                -1,
            ),
            Arguments.of(
                5,
                arrayOf(
                    intArrayOf(1, 4),
                    intArrayOf(2, 4),
                    intArrayOf(3, 2),
                    intArrayOf(3, 4),
                    intArrayOf(4, 2),
                    intArrayOf(5, 4),
                ),
                -1,
            ),
            Arguments.of(
                5,
                arrayOf(
                    intArrayOf(1, 2),
                    intArrayOf(2, 3),
                    intArrayOf(3, 1),
                    intArrayOf(3, 4),
                    intArrayOf(4, 3),
                    intArrayOf(4, 5),
                    intArrayOf(5, 4),
                ),
                -1,
            ),
            Arguments.of(
                5,
                arrayOf(
                    intArrayOf(1, 2),
                    intArrayOf(2, 3),
                    intArrayOf(3, 4),
                    intArrayOf(4, 5),
                    intArrayOf(5, 1),
                ),
                -1,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `find judge test`(num: Int, trust: Array<IntArray>, expected: Int) {
        val actual = strategy.invoke(num, trust)
        assertThat(actual, equalTo(expected))
    }
}

class FindJudgeTwoArraysTest : FindJudgeTest<FindJudgeTwoArrays>(FindJudgeTwoArrays())
class FindJudgeOneArrayTest : FindJudgeTest<FindJudgeOneArray>(FindJudgeOneArray())

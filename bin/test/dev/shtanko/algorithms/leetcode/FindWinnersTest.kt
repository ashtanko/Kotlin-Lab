/*
 * Copyright 2022 Oleksii Shtanko
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

abstract class FindWinnersTest<out T : FindWinners>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 3),
                    intArrayOf(2, 3),
                    intArrayOf(3, 6),
                    intArrayOf(5, 6),
                    intArrayOf(5, 7),
                    intArrayOf(4, 5),
                    intArrayOf(4, 8),
                    intArrayOf(4, 9),
                    intArrayOf(10, 4),
                    intArrayOf(10, 9),
                ),
                listOf(
                    listOf(1, 2, 10),
                    listOf(4, 5, 7, 8),
                ),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(2, 3),
                    intArrayOf(1, 3),
                    intArrayOf(5, 4),
                    intArrayOf(6, 4),
                ),
                listOf(
                    listOf(1, 2, 5, 6),
                    listOf(),
                ),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 2),
                    intArrayOf(2, 3),
                    intArrayOf(3, 4),
                    intArrayOf(4, 5),
                ),
                listOf(
                    listOf(1),
                    listOf(2, 3, 4, 5),
                ),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 2),
                    intArrayOf(2, 3),
                    intArrayOf(3, 4),
                    intArrayOf(4, 5),
                    intArrayOf(5, 6),
                    intArrayOf(6, 7),
                    intArrayOf(7, 8),
                    intArrayOf(8, 9),
                    intArrayOf(9, 10),
                    intArrayOf(10, 1),
                ),
                listOf(
                    listOf(),
                    listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
                ),
            ),
            Arguments.of(
                arrayOf(
                    intArrayOf(1, 2),
                    intArrayOf(2, 3),
                    intArrayOf(3, 4),
                    intArrayOf(4, 5),
                    intArrayOf(5, 6),
                    intArrayOf(6, 7),
                    intArrayOf(7, 8),
                    intArrayOf(8, 9),
                    intArrayOf(9, 10),
                    intArrayOf(10, 1),
                    intArrayOf(1, 3),
                    intArrayOf(3, 5),
                    intArrayOf(5, 7),
                    intArrayOf(7, 9),
                    intArrayOf(9, 2),
                    intArrayOf(2, 4),
                    intArrayOf(4, 6),
                    intArrayOf(6, 8),
                    intArrayOf(8, 10),
                    intArrayOf(10, 1),
                ),
                listOf(
                    listOf(),
                    listOf<Int>(),
                ),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `find winners test`(matches: Array<IntArray>, expected: List<List<Int>>) {
        val actual = strategy.invoke(matches)
        assertThat(actual).isEqualTo(expected)
    }
}

class FindWinnersHashSetTest : FindWinnersTest<FindWinners>(FindWinnersHashSet())
class FindWinnersSetMapTest : FindWinnersTest<FindWinners>(FindWinnersSetMap())
class FindWinnersMapTest : FindWinnersTest<FindWinners>(FindWinnersMap())
class FindWinnersCountingTest : FindWinnersTest<FindWinners>(FindWinnersCounting())

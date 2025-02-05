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

abstract class CombinationSum2Test<out T : CombinationSum2>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(10, 1, 2, 7, 6, 1, 5),
                8,
                listOf(
                    listOf(1, 1, 6),
                    listOf(1, 2, 5),
                    listOf(1, 7),
                    listOf(2, 6),
                ),
            ),
            Arguments.of(
                intArrayOf(2, 5, 2, 1, 2),
                5,
                listOf(
                    listOf(1, 2, 2),
                    listOf(5),
                ),
            ),
            Arguments.of(
                intArrayOf(),
                0,
                listOf(
                    listOf<Int>(),
                ),
            ),
            Arguments.of(
                intArrayOf(),
                1,
                listOf<List<Int>>(),
            ),
            Arguments.of(
                intArrayOf(5, 3, 8, 9, 6, 3, 3, 4, 5, 6, 7, 2, 3, 4, 5, 7, 8, 9),
                23,
                listOf(
                    listOf(2, 3, 3, 3, 3, 4, 5),
                    listOf(2, 3, 3, 3, 3, 9),
                    listOf(2, 3, 3, 3, 4, 8),
                    listOf(2, 3, 3, 3, 5, 7),
                    listOf(2, 3, 3, 3, 6, 6),
                    listOf(2, 3, 3, 4, 4, 7),
                    listOf(2, 3, 3, 4, 5, 6),
                    listOf(2, 3, 3, 5, 5, 5),
                    listOf(2, 3, 3, 6, 9),
                    listOf(2, 3, 3, 7, 8),
                    listOf(2, 3, 4, 4, 5, 5),
                    listOf(2, 3, 4, 5, 9),
                    listOf(2, 3, 4, 6, 8),
                    listOf(2, 3, 4, 7, 7),
                    listOf(2, 3, 5, 5, 8),
                    listOf(2, 3, 5, 6, 7),
                    listOf(2, 3, 9, 9),
                    listOf(2, 4, 4, 5, 8),
                    listOf(2, 4, 4, 6, 7),
                    listOf(2, 4, 5, 5, 7),
                    listOf(2, 4, 5, 6, 6),
                    listOf(2, 4, 8, 9),
                    listOf(2, 5, 5, 5, 6),
                    listOf(2, 5, 7, 9),
                    listOf(2, 5, 8, 8),
                    listOf(2, 6, 6, 9),
                    listOf(2, 6, 7, 8),
                    listOf(3, 3, 3, 3, 4, 7),
                    listOf(3, 3, 3, 3, 5, 6),
                    listOf(3, 3, 3, 4, 4, 6),
                    listOf(3, 3, 3, 4, 5, 5),
                    listOf(3, 3, 3, 5, 9),
                    listOf(3, 3, 3, 6, 8),
                    listOf(3, 3, 3, 7, 7),
                    listOf(3, 3, 4, 4, 9),
                    listOf(3, 3, 4, 5, 8),
                    listOf(3, 3, 4, 6, 7),
                    listOf(3, 3, 5, 5, 7),
                    listOf(3, 3, 5, 6, 6),
                    listOf(3, 3, 8, 9),
                    listOf(3, 4, 4, 5, 7),
                    listOf(3, 4, 4, 6, 6),
                    listOf(3, 4, 5, 5, 6),
                    listOf(3, 4, 7, 9),
                    listOf(3, 4, 8, 8),
                    listOf(3, 5, 6, 9),
                    listOf(3, 5, 7, 8),
                    listOf(3, 6, 6, 8),
                    listOf(3, 6, 7, 7),
                    listOf(4, 4, 5, 5, 5),
                    listOf(4, 4, 6, 9),
                    listOf(4, 4, 7, 8),
                    listOf(4, 5, 5, 9),
                    listOf(4, 5, 6, 8),
                    listOf(4, 5, 7, 7),
                    listOf(4, 6, 6, 7),
                    listOf(5, 5, 5, 8),
                    listOf(5, 5, 6, 7),
                    listOf(5, 9, 9),
                    listOf(6, 8, 9),
                    listOf(7, 7, 9),
                    listOf(7, 8, 8),
                ),
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
                10,
                listOf(
                    listOf(1, 2, 3, 4),
                    listOf(1, 2, 7),
                    listOf(1, 3, 6),
                    listOf(1, 4, 5),
                    listOf(1, 9),
                    listOf(2, 3, 5),
                    listOf(2, 8),
                    listOf(3, 7),
                    listOf(4, 6),
                    listOf(10),
                ),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `combination sum 2 test`(candidates: IntArray, target: Int, expected: List<List<Int>>) {
        val actual = strategy.invoke(candidates, target)
        assertThat(actual).isEqualTo(expected)
    }
}

class BacktrackingWithCountersTest : CombinationSum2Test<CombinationSum2>(BacktrackingWithCounters())
class BacktrackingWithIndexTest : CombinationSum2Test<CombinationSum2>(BacktrackingWithIndex())
class CombinationSum2CompactTest : CombinationSum2Test<CombinationSum2>(CombinationSum2Compact())

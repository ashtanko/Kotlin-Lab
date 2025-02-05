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

abstract class SubsetsTest<out T : Subsets>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(1, 2, 3),
                listOf(
                    listOf(),
                    listOf(1),
                    listOf(2),
                    listOf(1, 2),
                    listOf(3),
                    listOf(1, 3),
                    listOf(2, 3),
                    listOf(1, 2, 3),
                ),
            ),
            Arguments.of(
                intArrayOf(0),
                listOf(
                    listOf(),
                    listOf(0),
                ),
            ),
            Arguments.of(
                intArrayOf(1, 2),
                listOf(
                    listOf(),
                    listOf(1),
                    listOf(2),
                    listOf(1, 2),
                ),
            ),
            Arguments.of(
                intArrayOf(),
                listOf(
                    listOf<Int>(),
                ),
            ),
            Arguments.of(
                intArrayOf(1),
                listOf(
                    listOf(),
                    listOf(1),
                ),
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4),
                listOf(
                    listOf(),
                    listOf(1),
                    listOf(2),
                    listOf(1, 2),
                    listOf(3),
                    listOf(1, 3),
                    listOf(2, 3),
                    listOf(1, 2, 3),
                    listOf(4),
                    listOf(1, 4),
                    listOf(2, 4),
                    listOf(1, 2, 4),
                    listOf(3, 4),
                    listOf(1, 3, 4),
                    listOf(2, 3, 4),
                    listOf(1, 2, 3, 4),
                ),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `subsets test`(nums: IntArray, expected: List<List<Int>>) {
        val actual = strategy.invoke(nums)
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected)
    }
}

class CascadingSubsetsTest : SubsetsTest<Subsets>(CascadingSubsets())
class BacktrackingSubsetsTest : SubsetsTest<Subsets>(BacktrackingSubsets())
class LexicographicSubsetsTest : SubsetsTest<Subsets>(LexicographicSubsets())

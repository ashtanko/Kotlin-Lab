/*
 * Designed and developed by 2024 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
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
import org.junit.jupiter.params.support.ParameterDeclarations

sealed class LeftmostBuildingQueriesTest<out T : LeftmostBuildingQueries>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(
            parameters: ParameterDeclarations?,
            context: ExtensionContext?,
        ): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(6, 4, 8, 5, 2, 7),
                arrayOf(
                    intArrayOf(0, 1),
                    intArrayOf(0, 3),
                    intArrayOf(2, 4),
                    intArrayOf(3, 4),
                    intArrayOf(2, 2),
                ),
                intArrayOf(2, 5, -1, 5, 2),
            ),
            Arguments.of(
                intArrayOf(5, 3, 8, 2, 6, 1, 4, 6),
                arrayOf(
                    intArrayOf(0, 7),
                    intArrayOf(3, 5),
                    intArrayOf(5, 2),
                    intArrayOf(3, 0),
                    intArrayOf(1, 6),
                ),
                intArrayOf(7, 6, -1, 4, 6),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `leftmostBuildingQueries test`(heights: IntArray, queries: Array<IntArray>, expected: IntArray) {
        val actual = strategy(heights, queries)
        assertThat(actual).isEqualTo(expected)
    }

    data object MonotonicStackStrategyTest :
        LeftmostBuildingQueriesTest<LeftmostBuildingQueries.MonotonicStack>(
            LeftmostBuildingQueries.MonotonicStack,
        )

    data object PriorityQueueStrategyTest :
        LeftmostBuildingQueriesTest<LeftmostBuildingQueries.PriorityQueueStrategy>(
            LeftmostBuildingQueries.PriorityQueueStrategy,
        )
}

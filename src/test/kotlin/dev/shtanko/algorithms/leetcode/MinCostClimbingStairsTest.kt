/*
 * Designed and developed by 2020 ashtanko (Oleksii Shtanko)
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
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import org.junit.jupiter.params.support.ParameterDeclarations

abstract class MinCostClimbingStairsTest<out T : MinCostClimbingStairs>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(
            parameters: ParameterDeclarations?,
            context: ExtensionContext?,
        ): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(),
                0,
            ),
            Arguments.of(
                intArrayOf(0, 0, 0, 0),
                0,
            ),
            Arguments.of(
                intArrayOf(10, 15, 20),
                15,
            ),
            Arguments.of(
                intArrayOf(1, 100, 1, 1, 1, 100, 1, 1, 100, 1),
                6,
            ),
            Arguments.of(
                intArrayOf(2, 5, 16, 4, 1, 1, 7),
                10,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `min cost climbing stairs test`(cost: IntArray, expected: Int) {
        val actual = strategy.invoke(cost)
        assertThat(actual, equalTo(expected))
    }
}

class MinCostClimbingStairsRecursiveTest :
    MinCostClimbingStairsTest<MinCostClimbingStairsRecursive>(MinCostClimbingStairsRecursive())

class MinCostClimbingStairsMemoizationTest :
    MinCostClimbingStairsTest<MinCostClimbingStairsMemoization>(MinCostClimbingStairsMemoization())

class MinCostClimbingStairsDPBottomUpTest :
    MinCostClimbingStairsTest<MinCostClimbingStairsDPBottomUp>(MinCostClimbingStairsDPBottomUp())

class MinCostClimbingStairsDPOptimizedTest :
    MinCostClimbingStairsTest<MinCostClimbingStairsDPOptimized>(MinCostClimbingStairsDPOptimized())

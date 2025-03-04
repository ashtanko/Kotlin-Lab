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
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class AbstractMinSubsequenceStrategyTest<T : MinSubsequenceStrategy>(val strategy: T) {

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `min subsequence test`(arr: IntArray, expected: List<Int>) {
        val actual = strategy(arr)
        assertEquals(expected, actual)
    }

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(4, 3, 10, 9, 8),
                listOf(10, 9),
            ),
            Arguments.of(
                intArrayOf(4, 4, 7, 6, 7),
                listOf(7, 7, 6),
            ),
            Arguments.of(
                intArrayOf(6),
                listOf(6),
            ),
            Arguments.of(
                intArrayOf(1, 1, 1, 1, 1),
                listOf(1, 1, 1),
            ),
            Arguments.of(
                intArrayOf(1, 1, 1, 1, 1, 3, 5),
                listOf(5, 3),
            ),
            Arguments.of(
                intArrayOf(),
                listOf<Int>(),
            ),
        )
    }
}

class MinSubsequenceCountingSortTest :
    AbstractMinSubsequenceStrategyTest<MinSubsequenceCountingSort>(MinSubsequenceCountingSort())

class MinSubsequencePriorityQueueTest :
    AbstractMinSubsequenceStrategyTest<MinSubsequencePriorityQueue>(MinSubsequencePriorityQueue())

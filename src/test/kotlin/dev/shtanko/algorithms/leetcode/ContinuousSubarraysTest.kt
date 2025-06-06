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

sealed class ContinuousSubarraysTest<out T : ContinuousSubarrays>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(
            parameters: ParameterDeclarations?,
            context: ExtensionContext?,
        ): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(5, 4, 2, 4),
                8L,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3),
                6L,
            ),
        )
    }

    @ParameterizedTest(name = "{index} => nums = {0}, expected = {1}")
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `continuousSubarrays test`(nums: IntArray, expected: Long) {
        val actual = strategy(nums)
        assertThat(actual).isEqualTo(expected)
    }

    data object SortedMapTest : ContinuousSubarraysTest<ContinuousSubarrays.SortedMap>(ContinuousSubarrays.SortedMap)

    data object PriorityQueueStrategyTest :
        ContinuousSubarraysTest<ContinuousSubarrays.PriorityQueueStrategy>(ContinuousSubarrays.PriorityQueueStrategy)

    data object MonotonicDequeTest :
        ContinuousSubarraysTest<ContinuousSubarrays.MonotonicDeque>(ContinuousSubarrays.MonotonicDeque)

    data object OptimizedTwoPointerTest :
        ContinuousSubarraysTest<ContinuousSubarrays.OptimizedTwoPointer>(ContinuousSubarrays.OptimizedTwoPointer)
}

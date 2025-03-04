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

abstract class MaxSubarrayLenTest<out T : MaxSubarrayLen>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(1, 2, 3, 1, 2, 3, 1, 2),
                2,
                6,
            ),
            Arguments.of(
                intArrayOf(1, 2, 1, 2, 1, 2, 1, 2),
                1,
                2,
            ),
            Arguments.of(
                intArrayOf(5, 5, 5, 5, 5, 5, 5),
                4,
                4,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun maxSubarrayLengthTest(nums: IntArray, k: Int, expected: Int) {
        val actual = strategy(nums, k)
        assertThat(actual).isEqualTo(expected)
    }
}

class MaxSubarrayLenSlidingWindowTest :
    MaxSubarrayLenTest<MaxSubarrayLenSlidingWindow>(MaxSubarrayLenSlidingWindow())

class MaxSubarrayLenSlidingWindowOptTest :
    MaxSubarrayLenTest<MaxSubarrayLenSlidingWindowOpt>(MaxSubarrayLenSlidingWindowOpt())

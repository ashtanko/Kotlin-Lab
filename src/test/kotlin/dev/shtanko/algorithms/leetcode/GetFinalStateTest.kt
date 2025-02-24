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

sealed class GetFinalStateTest<out T : GetFinalState>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(),
                0,
                0,
                intArrayOf(),
            ),
            Arguments.of(
                intArrayOf(2, 1, 3, 5, 6),
                5,
                2,
                intArrayOf(8, 4, 6, 5, 6),
            ),
            Arguments.of(
                intArrayOf(1, 2),
                3,
                4,
                intArrayOf(16, 8),
            ),
        )
    }

    @ParameterizedTest(name = "nums: {0}, k: {1}, multiplier: {2} should return int: {3}")
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `getFinalState test`(nums: IntArray, k: Int, multiplier: Int, expected: IntArray) {
        val actual = strategy(nums, k, multiplier)
        assertThat(actual).isEqualTo(expected)
    }

    data object ArrayTest : GetFinalStateTest<GetFinalState>(GetFinalState.ArrayStrategy)
    data object HeapTest : GetFinalStateTest<GetFinalState>(GetFinalState.HeapStrategy)
}

/*
 * Copyright 2021 Oleksii Shtanko
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

abstract class PermutationsTest<out T : Permutations>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(1, 2, 3),
                listOf(
                    listOf(1, 2, 3),
                    listOf(1, 3, 2),
                    listOf(2, 1, 3),
                    listOf(2, 3, 1),
                    listOf(3, 1, 2),
                    listOf(3, 2, 1),
                ),
            ),
            Arguments.of(
                intArrayOf(0, 1),
                listOf(listOf(0, 1), listOf(1, 0)),
            ),
            Arguments.of(
                intArrayOf(1),
                listOf(listOf(1)),
            ),
            Arguments.of(
                intArrayOf(),
                listOf(listOf<Int>()),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `array permutations test`(nums: IntArray, expected: List<List<Int>>) {
        val actual = strategy.permute(nums)
        assertThat(actual).hasSameElementsAs(expected)
    }
}

class PermutationsBacktrackingTest : PermutationsTest<PermutationsBacktracking>(PermutationsBacktracking())

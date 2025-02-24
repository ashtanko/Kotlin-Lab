/*
 * Copyright 2023 Oleksii Shtanko
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

abstract class StoneGame2Test<out T : StoneGame2>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(2, 7, 9, 4, 4),
                10,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5, 100),
                104,
            ),
            Arguments.of(
                intArrayOf(),
                0,
            ),
            Arguments.of(
                intArrayOf(1),
                1,
            ),
            Arguments.of(
                intArrayOf(1, 2),
                3,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3),
                3,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4),
                5,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5),
                8,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5, 6),
                10,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5, 6, 7),
                15,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5, 6, 7, 8),
                18,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9),
                25,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `stone game II test`(piles: IntArray, expected: Int) {
        val actual = strategy.invoke(piles)
        assertThat(actual).isEqualTo(expected)
    }
}

class StoneGame2MemoizationTest : StoneGame2Test<StoneGame2>(StoneGame2WithMemoization())
class StoneGame2DPTest : StoneGame2Test<StoneGame2>(StoneGame2DP())

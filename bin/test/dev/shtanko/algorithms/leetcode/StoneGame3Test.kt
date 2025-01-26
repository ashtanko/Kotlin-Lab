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

abstract class StoneGame3Test<out T : StoneGame3>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(1, 2, 3, 7),
                "Bob",
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, -9),
                "Alice",
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 6),
                "Tie",
            ),
            Arguments.of(
                intArrayOf(),
                "Tie",
            ),
            Arguments.of(
                intArrayOf(1),
                "Alice",
            ),
            Arguments.of(
                intArrayOf(1, 2),
                "Alice",
            ),
            Arguments.of(
                intArrayOf(1, 2, 3),
                "Alice",
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4),
                "Alice",
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5),
                "Bob",
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `stone game III test`(stoneValue: IntArray, expected: String) {
        val actual = strategy.invoke(stoneValue)
        assertThat(actual).isEqualTo(expected)
    }
}

class StoneGame3BottomUpTest : StoneGame3Test<StoneGame3>(StoneGame3BottomUp())
class StoneGame3TopDownTest : StoneGame3Test<StoneGame3>(StoneGame3TopDown())
class StoneGame3OptimizedTest : StoneGame3Test<StoneGame3>(StoneGame3Optimized())

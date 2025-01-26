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
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class MinDifficultyTest<out T : MinDifficulty>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(6, 5, 4, 3, 2, 1),
                2,
                7,
            ),
            Arguments.of(
                intArrayOf(9, 9, 9),
                4,
                -1,
            ),
            Arguments.of(
                intArrayOf(1, 1, 1),
                3,
                3,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5),
                1,
                5,
            ),
            Arguments.of(
                intArrayOf(),
                0,
                0,
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5),
                6,
                -1,
            ),
            Arguments.of(
                intArrayOf(6, 5, 4, 3, 2, 1),
                3,
                9,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `min difficulty test`(jobDifficulty: IntArray, d: Int, expected: Int) {
        val actual = strategy(jobDifficulty, d)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class MinDifficultyTopDownTest : MinDifficultyTest<MinDifficulty>(MinDifficultyTopDown())
class MinDifficultyBottomUpTest : MinDifficultyTest<MinDifficulty>(MinDifficultyBottomUp())
class MinDifficultyBottomUp1DTest : MinDifficultyTest<MinDifficulty>(MinDifficultyBottomUp1D())
class MinDifficultyStackTest : MinDifficultyTest<MinDifficulty>(MinDifficultyStack())

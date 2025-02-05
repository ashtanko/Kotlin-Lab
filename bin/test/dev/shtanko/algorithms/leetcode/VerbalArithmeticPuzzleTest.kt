/*
 * Copyright 2022 Oleksii Shtanko
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

abstract class VerbalArithmeticPuzzleTest<out T : VerbalArithmeticPuzzle>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf("SEND", "MORE"),
                "MONEY",
                true,
            ),
            Arguments.of(
                arrayOf("SIX", "SEVEN", "SEVEN"),
                "TWENTY",
                true,
            ),
            Arguments.of(
                arrayOf("LEET", "CODE"),
                "POINT",
                false,
            ),
            Arguments.of(
                arrayOf<String>(),
                "",
                true,
            ),
            Arguments.of(
                arrayOf("A"),
                "",
                true,
            ),
            Arguments.of(
                arrayOf("A"),
                "A",
                true,
            ),
            Arguments.of(
                arrayOf<String>(),
                "A",
                true,
            ),
            Arguments.of(
                arrayOf("A", "B"),
                "C",
                true,
            ),
            Arguments.of(
                arrayOf("A", "B"),
                "D",
                true,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `is solvable test`(words: Array<String>, result: String, expected: Boolean) {
        val actual = strategy.invoke(words, result)
        assertThat(actual).isEqualTo(expected)
    }
}

class VerbalArithmeticPuzzleBacktrackingTest :
    VerbalArithmeticPuzzleTest<VerbalArithmeticPuzzle>(VerbalArithmeticPuzzleBacktracking())

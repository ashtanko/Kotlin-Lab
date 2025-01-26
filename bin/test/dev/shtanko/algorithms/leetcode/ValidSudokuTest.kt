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

abstract class ValidSudokuTest<out T : ValidSudoku>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                arrayOf(
                    charArrayOf('5', '3', '.', '.', '7', '.', '.', '.', '.'),
                    charArrayOf('6', '.', '.', '1', '9', '5', '.', '.', '.'),
                    charArrayOf('.', '9', '8', '.', '.', '.', '.', '6', '.'),
                    charArrayOf('8', '.', '.', '.', '6', '.', '.', '.', '3'),
                    charArrayOf('4', '.', '.', '8', '.', '3', '.', '.', '1'),
                    charArrayOf('7', '.', '.', '.', '2', '.', '.', '.', '6'),
                    charArrayOf('.', '6', '.', '.', '.', '.', '2', '8', '.'),
                    charArrayOf('.', '.', '.', '4', '1', '9', '.', '.', '5'),
                    charArrayOf('.', '.', '.', '.', '8', '.', '.', '7', '9'),
                ),
                true,
            ),
            Arguments.of(
                arrayOf(
                    charArrayOf('8', '3', '.', '.', '7', '.', '.', '.', '.'),
                    charArrayOf('6', '.', '.', '1', '9', '5', '.', '.', '.'),
                    charArrayOf('.', '9', '8', '.', '.', '.', '.', '6', '.'),
                    charArrayOf('8', '.', '.', '.', '6', '.', '.', '.', '3'),
                    charArrayOf('4', '.', '.', '8', '.', '3', '.', '.', '1'),
                    charArrayOf('7', '.', '.', '.', '2', '.', '.', '.', '6'),
                    charArrayOf('.', '6', '.', '.', '.', '.', '2', '8', '.'),
                    charArrayOf('.', '.', '.', '4', '1', '9', '.', '.', '5'),
                    charArrayOf('.', '.', '.', '.', '8', '.', '.', '7', '9'),
                ),
                false,
            ),
            Arguments.of(
                arrayOf(
                    charArrayOf('.', '.', '.', '.', '5', '.', '.', '1', '.'),
                    charArrayOf('.', '4', '.', '3', '.', '.', '.', '.', '.'),
                    charArrayOf('.', '.', '.', '.', '.', '3', '.', '.', '1'),
                    charArrayOf('8', '.', '.', '.', '.', '.', '.', '2', '.'),
                    charArrayOf('.', '.', '2', '.', '7', '.', '.', '.', '.'),
                    charArrayOf('.', '1', '5', '.', '.', '.', '.', '.', '.'),
                    charArrayOf('.', '.', '.', '.', '.', '2', '.', '.', '.'),
                    charArrayOf('.', '2', '.', '9', '.', '.', '.', '.', '.'),
                    charArrayOf('.', '.', '4', '.', '.', '.', '.', '.', '.'),
                ),
                false,
            ),
            Arguments.of(
                arrayOf(
                    charArrayOf('.', '.', '.', '.', '5', '.', '.', '1', '.'),
                    charArrayOf('.', '4', '.', '3', '.', '.', '.', '.', '.'),
                    charArrayOf('.', '.', '.', '.', '.', '3', '.', '.', '1'),
                    charArrayOf('8', '.', '.', '.', '.', '.', '.', '2', '.'),
                    charArrayOf('.', '.', '2', '.', '7', '.', '.', '.', '.'),
                    charArrayOf('.', '1', '5', '.', '.', '.', '.', '.', '.'),
                    charArrayOf('.', '.', '.', '.', '.', '2', '.', '.', '.'),
                    charArrayOf('.', '2', '.', '9', '.', '.', '.', '.', '.'),
                    charArrayOf('.', '.', '4', '.', '.', '.', '.', '.', '9'),
                ),
                false,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `is valid sudoku test`(board: Array<CharArray>, expected: Boolean) {
        val actual = strategy.invoke(board)
        assertThat(actual).isEqualTo(expected)
    }
}

class ValidSudokuSimpleTest : ValidSudokuTest<ValidSudoku>(ValidSudokuSimple())

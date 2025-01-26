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

package dev.shtanko.algorithms.backtracking

import java.util.stream.Stream
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class NQueensTest<out T : NQueens>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                0,
                listOf(emptyList<List<String>>()),
            ),
            Arguments.of(
                1,
                listOf(
                    listOf("Q"),
                ),
            ),
            Arguments.of(
                2,
                emptyList<List<String>>(),
            ),
            Arguments.of(
                3,
                emptyList<List<String>>(),
            ),
            Arguments.of(
                4,
                listOf(
                    listOf(".Q..", "...Q", "Q...", "..Q."),
                    listOf("..Q.", "Q...", "...Q", ".Q.."),
                ),
            ),
            Arguments.of(
                5,
                listOf(
                    listOf("Q....", "..Q..", "....Q", ".Q...", "...Q."),
                    listOf("Q....", "...Q.", ".Q...", "....Q", "..Q.."),
                    listOf(".Q...", "...Q.", "Q....", "..Q..", "....Q"),
                    listOf(".Q...", "....Q", "..Q..", "Q....", "...Q."),
                    listOf("..Q..", "Q....", "...Q.", ".Q...", "....Q"),
                    listOf("..Q..", "....Q", ".Q...", "...Q.", "Q...."),
                    listOf("...Q.", "Q....", "..Q..", "....Q", ".Q..."),
                    listOf("...Q.", ".Q...", "....Q", "..Q..", "Q...."),
                    listOf("....Q", ".Q...", "...Q.", "Q....", "..Q.."),
                    listOf("....Q", "..Q..", "Q....", "...Q.", ".Q..."),
                ),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `n queens test`(num: Int, expected: List<List<String>>) {
        val actual = strategy(num)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class NQueensSolutionTest : NQueensTest<NQueens>(NQueensSolution())

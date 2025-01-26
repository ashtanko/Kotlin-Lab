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

abstract class EvaluateDivisionTest<out T : EvaluateDivision>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                listOf(
                    listOf("a", "b"),
                    listOf("b", "c"),
                ),
                doubleArrayOf(2.0, 3.0),
                listOf(
                    listOf("a", "c"),
                    listOf("b", "a"),
                    listOf("a", "e"),
                    listOf("a", "a"),
                    listOf("x", "x"),
                ),
                doubleArrayOf(6.00000, 0.50000, -1.00000, 1.00000, -1.00000),
            ),
            Arguments.of(
                listOf(
                    listOf("a", "b"),
                    listOf("b", "c"),
                    listOf("bc", "cd"),
                ),
                doubleArrayOf(1.5, 2.5, 5.0),
                listOf(
                    listOf("a", "c"),
                    listOf("c", "b"),
                    listOf("bc", "cd"),
                    listOf("cd", "bc"),
                ),
                doubleArrayOf(3.75000, 0.40000, 5.00000, 0.20000),
            ),
            Arguments.of(
                listOf(listOf("a", "b")),
                doubleArrayOf(0.5),
                listOf(
                    listOf("a", "b"),
                    listOf("b", "a"),
                    listOf("a", "c"),
                    listOf("x", "y"),
                ),
                doubleArrayOf(0.50000, 2.00000, -1.00000, -1.00000),
            ),
            Arguments.of(
                listOf(
                    listOf("a", "b"),
                    listOf("c", "d"),
                ),
                doubleArrayOf(1.0, 1.0),
                listOf(
                    listOf("a", "c"),
                    listOf("b", "d"),
                    listOf("b", "a"),
                    listOf("d", "c"),
                ),
                doubleArrayOf(-1.00000, -1.00000, 1.00000, 1.00000),
            ),
            Arguments.of(
                listOf(
                    listOf("a", "b"),
                    listOf("b", "c"),
                    listOf("c", "d"),
                    listOf("d", "e"),
                ),
                doubleArrayOf(2.0, 3.0, 4.0, 5.0),
                listOf(
                    listOf("a", "e"),
                    listOf("e", "a"),
                    listOf("a", "c"),
                    listOf("a", "d"),
                    listOf("a", "a"),
                    listOf("x", "x"),
                ),
                doubleArrayOf(120.0, 0.008333333333333333, 6.0, 24.0, 1.0, -1.0),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `calc equation test`(
        equations: List<List<String>>,
        values: DoubleArray,
        queries: List<List<String>>,
        expected: DoubleArray,
    ) {
        val actual = strategy.calcEquation(equations, values, queries)
        assertThat(actual).isEqualTo(expected)
    }
}

class EvaluateDivisionDFSTest : EvaluateDivisionTest<EvaluateDivision>(EvaluateDivisionDFS())

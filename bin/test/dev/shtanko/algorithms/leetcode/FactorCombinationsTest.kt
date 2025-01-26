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

abstract class FactorCombinationsTest<out T : FactorCombinations>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(0, emptyList<List<Int>>()),
            Arguments.of(1, emptyList<List<Int>>()),
            Arguments.of(
                12,
                listOf(
                    listOf(2, 6),
                    listOf(3, 4),
                    listOf(2, 2, 3),
                ),
            ),
            Arguments.of(37, emptyList<List<Int>>()),
            Arguments.of(
                32,
                listOf(
                    listOf(2, 16),
                    listOf(4, 8),
                    listOf(2, 2, 8),
                    listOf(2, 4, 4),
                    listOf(2, 2, 2, 4),
                    listOf(2, 2, 2, 2, 2),
                ),
            ),
            Arguments.of(
                15,
                listOf(
                    listOf(3, 5),
                ),
            ),
            Arguments.of(
                8,
                listOf(
                    listOf(2, 2, 2),
                    listOf(2, 4),
                ),
            ),
            Arguments.of(
                9,
                listOf(
                    listOf(3, 3),
                ),
            ),
            Arguments.of(
                100,
                listOf(
                    listOf(2, 2, 5, 5),
                    listOf(2, 2, 25),
                    listOf(2, 5, 10),
                    listOf(2, 50),
                    listOf(4, 5, 5),
                    listOf(4, 25),
                    listOf(5, 20),
                    listOf(10, 10),
                ),
            ),
            Arguments.of(
                32,
                listOf(
                    listOf(2, 16),
                    listOf(4, 8),
                    listOf(2, 2, 8),
                    listOf(2, 4, 4),
                    listOf(2, 2, 2, 4),
                    listOf(2, 2, 2, 2, 2),
                ),
            ),
            Arguments.of(
                23848713,
                emptyList<List<Int>>(),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `get factors test`(num: Int, expected: List<List<Int>>) {
        val actual = strategy.invoke(num)
        assertThat(actual).containsAll(expected)
    }
}

class FactorCombinations1Test : FactorCombinationsTest<FactorCombinations>(FactorCombinationsImpl())
class FactorCombinations2Test : FactorCombinationsTest<FactorCombinations>(FactorCombinationsImpl2())

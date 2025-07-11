/*
 * Designed and developed by 2022 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
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
import org.junit.jupiter.params.support.ParameterDeclarations

abstract class CombinationsTest<out T : Combinations>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(
            parameters: ParameterDeclarations?,
            context: ExtensionContext?,
        ): Stream<out Arguments> = Stream.of(
            Arguments.of(
                4,
                2,
                listOf(
                    listOf(1, 2),
                    listOf(1, 3),
                    listOf(1, 4),
                    listOf(2, 3),
                    listOf(2, 4),
                    listOf(3, 4),
                ),
            ),
            Arguments.of(
                1,
                1,
                listOf(
                    listOf(1),
                ),
            ),
            Arguments.of(
                0,
                0,
                listOf(
                    listOf<Int>(),
                ),
            ),
            Arguments.of(
                6,
                1,
                listOf(
                    listOf(1),
                    listOf(2),
                    listOf(3),
                    listOf(4),
                    listOf(5),
                    listOf(6),
                ),
            ),
            Arguments.of(
                4,
                4,
                listOf(
                    listOf(1, 2, 3, 4),
                ),
            ),
            Arguments.of(
                4,
                3,
                listOf(
                    listOf(1, 2, 3),
                    listOf(1, 2, 4),
                    listOf(1, 3, 4),
                    listOf(2, 3, 4),
                ),
            ),
            Arguments.of(
                5,
                3,
                listOf(
                    listOf(1, 2, 3),
                    listOf(1, 2, 4),
                    listOf(1, 2, 5),
                    listOf(1, 3, 4),
                    listOf(1, 3, 5),
                    listOf(1, 4, 5),
                    listOf(2, 3, 4),
                    listOf(2, 3, 5),
                    listOf(2, 4, 5),
                    listOf(3, 4, 5),
                ),
            ),
            Arguments.of(
                5,
                2,
                listOf(
                    listOf(1, 2),
                    listOf(1, 3),
                    listOf(1, 4),
                    listOf(1, 5),
                    listOf(2, 3),
                    listOf(2, 4),
                    listOf(2, 5),
                    listOf(3, 4),
                    listOf(3, 5),
                    listOf(4, 5),
                ),
            ),
            Arguments.of(
                5,
                1,
                listOf(
                    listOf(1),
                    listOf(2),
                    listOf(3),
                    listOf(4),
                    listOf(5),
                ),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `combine test`(num: Int, k: Int, expected: List<List<Int>>) {
        val actual = strategy.invoke(num, k)
        assertThat(actual).isEqualTo(expected)
    }
}

class CombinationsBacktrackingTest : CombinationsTest<Combinations>(CombinationsBacktracking())

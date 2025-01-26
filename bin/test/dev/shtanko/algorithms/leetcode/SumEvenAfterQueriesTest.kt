/*
 * Copyright 2020 Oleksii Shtanko
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

class SumEvenAfterQueriesTest {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                intArrayOf(1, 2, 3, 4),
                arrayOf(
                    intArrayOf(1, 0),
                    intArrayOf(-3, 1),
                    intArrayOf(-4, 0),
                    intArrayOf(2, 3),
                ),
                intArrayOf(8, 6, 2, 4),
            ),
            Arguments.of(
                intArrayOf(),
                arrayOf(
                    intArrayOf(),
                ),
                intArrayOf(),
            ),
            Arguments.of(
                intArrayOf(),
                arrayOf<IntArray>(),
                intArrayOf(),
            ),
            Arguments.of(
                intArrayOf(0),
                arrayOf<IntArray>(),
                intArrayOf(),
            ),
            Arguments.of(
                intArrayOf(1),
                arrayOf(
                    intArrayOf(4, 0),
                ),
                intArrayOf(0),
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5),
                arrayOf(
                    intArrayOf(1, 0),
                    intArrayOf(-3, 1),
                    intArrayOf(-4, 0),
                    intArrayOf(2, 3),
                ),
                intArrayOf(8, 6, 2, 4),
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5),
                arrayOf(
                    intArrayOf(1, 0),
                    intArrayOf(-3, 1),
                    intArrayOf(-4, 0),
                    intArrayOf(2, 3),
                    intArrayOf(0, 0),
                ),
                intArrayOf(8, 6, 2, 4, 4),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `sum even after queries test`(a: IntArray, queries: Array<IntArray>, expected: IntArray) {
        val actual = calculateSumEvenAfterQueries(a, queries)
        assertThat(actual).isEqualTo(expected)
    }
}

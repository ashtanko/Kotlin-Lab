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

package dev.shtanko.algorithms.dp

import java.util.stream.Stream
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

internal class FibonacciSequenceTest {

    internal class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                0,
                0L,
            ),
            Arguments.of(
                1,
                1L,
            ),
            Arguments.of(
                2,
                1L,
            ),
            Arguments.of(
                3,
                2L,
            ),

            Arguments.of(
                4,
                3L,
            ),
            Arguments.of(
                5,
                5L,
            ),
            Arguments.of(
                6,
                8L,
            ),
            Arguments.of(
                7,
                13L,
            ),
            Arguments.of(
                8,
                21L,
            ),
        )
    }

    internal class SequenceArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                1,
                listOf(0),
            ),
            Arguments.of(
                10,
                listOf(0, 1, 1, 2, 3, 5, 8, 13, 21, 34),
            ),
            Arguments.of(
                22,
                listOf(
                    0,
                    1,
                    1,
                    2,
                    3,
                    5,
                    8,
                    13,
                    21,
                    34,
                    55,
                    89,
                    144,
                    233,
                    377,
                    610,
                    987,
                    1597,
                    2584,
                    4181,
                    6765,
                    10946,
                ),
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    internal fun `fibonacci number test`(num: Int, expected: Long) {
        val actual = fibonacciAt(num)
        assertEquals(expected, actual)
    }

    @ParameterizedTest
    @ArgumentsSource(SequenceArgumentsProvider::class)
    internal fun `fibonacci sequence test`(num: Int, expected: List<Int>) {
        val actual = fibonacci().take(num).toList()
        assertEquals(expected, actual)
    }
}

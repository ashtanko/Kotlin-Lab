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

package dev.shtanko.algorithms.hackerrank

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

internal class MarkAndToysTest {
    internal class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                0,
                intArrayOf(),
                0,
            ),
            Arguments.of(
                50,
                intArrayOf(1, 12, 5, 111, 200, 1000, 10),
                4,
            ),
            Arguments.of(
                100000,
                intArrayOf(
                    33324560,
                    77661073,
                    31948330,
                    21522343,
                    97176507,
                    5724692,
                    24699815,
                    12079402,
                    6479353,
                    28430129,
                    42427721,
                    57127004,
                    26256001,
                    29446837,
                    65107604,
                    9809008,
                    65846182,
                    8470661,
                    13597655,
                    360,
                ),
                1,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    internal fun `perform test`(money: Int, prices: IntArray, expected: Int) {
        val actual = MarkAndToys.perform(money, prices)
        assertThat(actual).isEqualTo(expected)
    }
}

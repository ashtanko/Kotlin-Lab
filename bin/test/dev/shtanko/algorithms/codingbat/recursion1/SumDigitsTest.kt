/*
 * Copyright 2024 Oleksii Shtanko
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

package dev.shtanko.algorithms.codingbat.recursion1

import java.util.stream.Stream
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

internal abstract class SumDigitsTest<out T : SumDigits>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                126,
                9,
            ),
            Arguments.of(
                49,
                13,
            ),
            Arguments.of(
                12,
                3,
            ),
            Arguments.of(
                111,
                3,
            ),
            Arguments.of(
                1,
                1,
            ),
            Arguments.of(
                10110,
                3,
            ),
            Arguments.of(
                0,
                0,
            ),
            Arguments.of(
                235,
                10,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `sum digits test`(digits: Int, expected: Int) {
        val actual = strategy(digits)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

internal class SumDigitsIterativeTest : SumDigitsTest<SumDigits>(SumDigitsIterative())
internal class SumDigitsRecursiveTest : SumDigitsTest<SumDigits>(SumDigitsRecursive())

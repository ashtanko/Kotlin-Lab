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

internal abstract class CountSevenTest<out T : CountSeven>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                717,
                2,
            ),
            Arguments.of(
                7,
                1,
            ),
            Arguments.of(
                123,
                0,
            ),
            Arguments.of(
                1,
                0,
            ),
            Arguments.of(
                -1,
                0,
            ),
            Arguments.of(
                777,
                3,
            ),
            Arguments.of(
                77777,
                5,
            ),
            Arguments.of(
                777576197,
                5,
            ),
            Arguments.of(
                70701277,
                4,
            ),
            Arguments.of(
                99999,
                0,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `count 7 test`(num: Int, expected: Int) {
        val actual = strategy(num)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

internal class CountSevenIterativeTest : CountSevenTest<CountSeven>(CountSevenIterative())
internal class CountSevenRecursiveTest : CountSevenTest<CountSeven>(CountSevenRecursive())
internal class CountSevenMemoTest : CountSevenTest<CountSeven>(CountSevenMemo())

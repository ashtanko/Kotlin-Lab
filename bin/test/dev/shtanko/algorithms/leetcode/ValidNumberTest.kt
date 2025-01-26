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
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

class ValidNumberTest {

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `is number using regex test`(str: String, expected: Boolean) {
        val actual = str.isNumberRegex()
        assertEquals(expected, actual)
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `is number test`(str: String, expected: Boolean) {
        val actual = str.isNumber()
        assertEquals(expected, actual)
    }

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "0",
                true,
            ),
            Arguments.of(
                " 0.1 ",
                true,
            ),
            Arguments.of(
                "abc",
                false,
            ),
            Arguments.of(
                "1 a",
                false,
            ),
            Arguments.of(
                "2e10",
                true,
            ),
            Arguments.of(
                " -90e3   ",
                true,
            ),
            Arguments.of(
                " 1e",
                false,
            ),
            Arguments.of(
                "e3",
                false,
            ),
            Arguments.of(
                " 6e-1",
                true,
            ),
            Arguments.of(
                " 99e2.5 ",
                false,
            ),
            Arguments.of(
                "53.5e93",
                true,
            ),
            Arguments.of(
                " --6 ",
                false,
            ),
            Arguments.of(
                "-+3",
                false,
            ),
            Arguments.of(
                "95a54e53",
                false,
            ),
            Arguments.of(
                "-1",
                true,
            ),
            Arguments.of(
                "",
                false,
            ),
            Arguments.of(
                ".1",
                true,
            ),
            Arguments.of(
                "3.",
                true,
            ),
            Arguments.of(
                ".",
                false,
            ),
            Arguments.of(
                "1.",
                true,
            ),
            Arguments.of(
                "1.e1",
                true,
            ),
        )
    }
}

/*
 * Copyright 2022 Oleksii Shtanko
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

abstract class CanBeValidTest<out T : CanBeValid>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "))()))",
                "010100",
                true,
            ),
            Arguments.of(
                "()()",
                "0000",
                true,
            ),
            Arguments.of(
                ")",
                "0",
                false,
            ),
            Arguments.of(
                "(",
                "0",
                false,
            ),
            Arguments.of(
                ")",
                "1",
                false,
            ),
            Arguments.of(
                "(",
                "1",
                false,
            ),
            Arguments.of(
                "",
                "",
                true,
            ),
            Arguments.of(
                "(",
                "",
                false,
            ),
            Arguments.of(
                "",
                "0",
                true,
            ),
            Arguments.of(
                "",
                "01",
                true,
            ),
            Arguments.of(
                "(()",
                "",
                false,
            ),
            Arguments.of(
                "(()",
                "0",
                false,
            ),
            Arguments.of(
                "(()",
                "01",
                false,
            ),
            Arguments.of(
                "(()",
                "010",
                false,
            ),
            Arguments.of(
                "(()",
                "011",
                false,
            ),
            Arguments.of(
                "(()",
                "10",
                false,
            ),
            Arguments.of(
                "(()",
                "11",
                false,
            ),
            Arguments.of(
                "(()",
                "100",
                false,
            ),
            Arguments.of(
                "(()",
                "101",
                false,
            ),
            Arguments.of(
                "(()",
                "110",
                false,
            ),
            Arguments.of(
                "(()",
                "111",
                false,
            ),
            Arguments.of(
                "(()",
                "1110",
                false,
            ),
            Arguments.of(
                "(()",
                "1111",
                false,
            ),
            Arguments.of(
                "(()",
                "11110",
                false,
            ),
            Arguments.of(
                "(()",
                "11111",
                false,
            ),
            Arguments.of(
                "(()",
                "111110",
                false,
            ),
            Arguments.of(
                "(()",
                "111111",
                false,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `can be valid test`(str: String, locked: String, expected: Boolean) {
        val actual = strategy.invoke(str, locked)
        assertThat(actual).isEqualTo(expected)
    }
}

class CanBeValidLeftRightTest : CanBeValidTest<CanBeValid>(CanBeValidLeftRight())
class CanBeValidCountingBracketsTest : CanBeValidTest<CanBeValid>(CanBeValidCountingBrackets())

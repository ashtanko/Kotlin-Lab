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

package dev.shtanko.algorithms.codingbat

import java.util.stream.Stream
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class NestParenTest<out T : NestParen>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "(())",
                true,
            ),
            Arguments.of(
                "((()))",
                true,
            ),
            Arguments.of(
                "(((x))",
                false,
            ),
            Arguments.of(
                "((())",
                false,
            ),

            Arguments.of(
                "((()()",
                false,
            ),
            Arguments.of(
                "()",
                true,
            ),
            Arguments.of(
                "",
                true,
            ),
            Arguments.of(
                "(yy)",
                false,
            ),
            Arguments.of(
                "(((y))",
                false,
            ),
            Arguments.of(
                "((y)))",
                false,
            ),
            Arguments.of(
                "((()))",
                true,
            ),
            Arguments.of(
                "(())))",
                false,
            ),
            Arguments.of(
                "((yy())))",
                false,
            ),
            Arguments.of(
                "(((())))",
                true,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `nestParen test`(str: String, expected: Boolean) {
        val actual = strategy(str)
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}

class NestParenIterativeTest : NestParenTest<NestParen>(NestParenIterative())
class NestParenRecursiveTest : NestParenTest<NestParen>(NestParenRecursive())

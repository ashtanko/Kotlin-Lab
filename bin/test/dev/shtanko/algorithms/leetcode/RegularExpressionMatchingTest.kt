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

abstract class RegularExpressionMatchingStrategyTest<out T : RegularExpressionMatch>(private val strategy: T) {

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `regular expression matching test`(text: String, pattern: String, expected: Boolean) {
        val actual = strategy.invoke(text = text, pattern = pattern)
        assertEquals(expected, actual)
    }

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "aa",
                "a",
                false,
            ),
            Arguments.of(
                "aa",
                "a*",
                true,
            ),
            Arguments.of(
                "ab",
                ".*",
                true,
            ),
            Arguments.of(
                "aab",
                "c*a*b",
                true,
            ),
            Arguments.of(
                "mississippi",
                "mis*is*p*.",
                false,
            ),
            Arguments.of(
                "",
                "",
                true,
            ),
        )
    }
}

class RegularExpressionMatchRecursionTest :
    RegularExpressionMatchingStrategyTest<RegularExpressionMatch>(RegularExpressionMatchRecursion())

class RegularExpressionMatchDPTopDownTest :
    RegularExpressionMatchingStrategyTest<RegularExpressionMatch>(RegularExpressionMatchDPTopDown())

class RegularExpressionMatchDPBottomUpTest :
    RegularExpressionMatchingStrategyTest<RegularExpressionMatch>(RegularExpressionMatchDPBottomUp())

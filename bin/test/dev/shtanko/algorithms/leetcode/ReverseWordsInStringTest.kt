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

package dev.shtanko.algorithms.leetcode

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class ReverseWordsInStringTest<out T : ReverseWordsInString>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                "the sky is blue",
                "blue is sky the",
            ),
            Arguments.of(
                "  hello world  ",
                "world hello",
            ),
            Arguments.of(
                "a good   example",
                "example good a",
            ),
            Arguments.of(
                "",
                "",
            ),
            Arguments.of(
                "  Bob    Loves  Alice   ",
                "Alice Loves Bob",
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun reverseWordsTest(str: String, expected: String) {
        val actual = strategy(str)
        assertThat(actual).isEqualTo(expected)
    }
}

class ReverseWordsInStringTwoPointersTest :
    ReverseWordsInStringTest<ReverseWordsInString>(ReverseWordsInStringTwoPointers())

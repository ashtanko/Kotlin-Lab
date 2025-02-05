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
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

class BoldWordsInStringTest {

    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(arrayOf<String>(), "", ""),
            Arguments.of(arrayOf("ab", "bc"), "aabcd", "a<b>abc</b>d"),
            Arguments.of(arrayOf("ab"), "aabcd", "a<b>ab</b>cd"),
            Arguments.of(arrayOf("ab"), "", ""),
            Arguments.of(arrayOf("ab"), "a", "a"),
            Arguments.of(arrayOf("ab"), "ab", "<b>ab</b>"),
            Arguments.of(arrayOf("a", "b"), "ab", "<b>ab</b>"),
            Arguments.of(arrayOf("a", "c"), "ab", "<b>a</b>b"),
            Arguments.of(arrayOf("a", "b", "c"), "abc", "<b>abc</b>"),
            Arguments.of(arrayOf("a", "b", "c"), "ab", "<b>ab</b>"),
            Arguments.of(arrayOf("a", "b", "c"), "bc", "<b>bc</b>"),
            Arguments.of(arrayOf("a", "b", "c"), "ac", "<b>ac</b>"),
            Arguments.of(arrayOf("a", "b", "c"), "a", "<b>a</b>"),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `bold words in string test`(words: Array<String>, str: String, expected: String) {
        val solution = BoldWordsInString()
        val actual = solution.invoke(words, str)
        assertThat(actual, equalTo(expected))
    }
}

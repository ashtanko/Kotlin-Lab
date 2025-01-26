/*
 * Copyright 2023 Oleksii Shtanko
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
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class LongestPalindromicSubstringTest<out T : LongestPalindromicSubstring>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of("", arrayOf("")),
            Arguments.of("a", arrayOf("a")),
            Arguments.of("aa", arrayOf("aa")),
            Arguments.of("aab", arrayOf("aa")),
            Arguments.of("babad", arrayOf("bab", "aba")),
            Arguments.of("cbbd", arrayOf("bb")),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `longest palindrome test`(s: String, expected: Array<String>) {
        val actual = strategy(s)
        Assertions.assertThat(arrayOf(actual)).containsAnyElementsOf(expected.toList())
    }
}

class LongestPalindromicSubstringBFTest :
    LongestPalindromicSubstringTest<LongestPalindromicSubstring>(LongestPalindromicSubstringBF())

class LongestPalindromicSubstringDPTest :
    LongestPalindromicSubstringTest<LongestPalindromicSubstring>(LongestPalindromicSubstringDP())

class LongestPalindromicSubstringExpandTest :
    LongestPalindromicSubstringTest<LongestPalindromicSubstring>(LongestPalindromicSubstringExpand())

class LongestPalindromicSubstringManacherTest :
    LongestPalindromicSubstringTest<LongestPalindromicSubstring>(LongestPalindromicSubstringManacher())
